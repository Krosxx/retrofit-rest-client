package cn.vove7.plugin.rest.action

import cn.vove7.plugin.rest.filetype.RequestParser
import cn.vove7.plugin.rest.filetype.RestFile
import cn.vove7.plugin.rest.model.RequestModel
import cn.vove7.plugin.rest.tool.EnvConfig
import cn.vove7.plugin.rest.tool.RequestExecutor
import cn.vove7.plugin.rest.tool.getFormattedResponse
import com.intellij.icons.AllIcons
import com.intellij.lang.Language
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.editor.Document
import com.intellij.openapi.fileEditor.TextEditor
import com.intellij.openapi.project.Project
import com.intellij.openapi.ui.popup.JBPopupFactory
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiFile
import com.intellij.psi.codeStyle.CodeStyleManager
import com.intellij.psi.impl.PsiFileFactoryImpl
import com.intellij.ui.awt.RelativePoint
import java.io.IOException
import java.time.LocalDateTime

class RunAction @JvmOverloads constructor(
        private val executor: RequestExecutor? = null,
        private val textEditor: TextEditor? = null
) : AnAction("Execute http request", "", AllIcons.Actions.Execute) {

    override fun update(e: AnActionEvent) {
        super.update(e)
        e.presentation.isEnabled = executor?.isWaiting == true
    }

    override fun actionPerformed(e: AnActionEvent) {
        val editor = textEditor?.editor ?: return
        if (executor?.isRunning == true) {
            return
        }

        val project = editor.project ?: return
        val document = editor.document

        val restFile = getRestFile(project, document) ?: return
        val requestModel = getRequestModel(restFile)

        val run = fun(env: Map<String, Any>?) {
            runWithConfig(env, project, requestModel, document, restFile)
        }

        if (requestModel.hasUnfilledParam()) {
            val envConfig = EnvConfig(project)
            if (envConfig.hasConfig()) {
                showEnvPopup(envConfig) { run(envConfig.getEnv(it)) }
            } else run(null)
        } else {
            run(null)
        }
    }

    private fun showEnvPopup(envConfig: EnvConfig, onChosen: (name: String) -> Unit) {
        val editor = textEditor?.editor ?: return

        val envs = envConfig.allEnv()
        val envWithUrl = envs.map {
            it.toUpperCase() + " " + envConfig.getEnv(it)["BASE_URL", ""]
        }
        JBPopupFactory.getInstance().createPopupChooserBuilder<String>(envWithUrl).setTitle("Choose env").setItemChosenCallback {
            onChosen.invoke(envs[envWithUrl.indexOf(it)])
        }.createPopup().show(RelativePoint.getNorthWestOf(editor.component))
    }

    operator fun <K, V> Map<K, V>.get(k: K, dv: V): V = getOrDefault(k, dv)


    private fun runWithConfig(
            env: Map<String, Any>?,
            project: Project,
            requestModel: RequestModel,
            document: Document,
            restFile: RestFile
    ) {
        ApplicationManager.getApplication().executeOnPooledThread a@{
            if (env != null) {//requestModel.hasUnfilledParam() == true
                requestModel.fill(env)
            }
            val requestEntity = requestModel.toRestFileContent(project).lines().filter { it.isNotEmpty() }.joinToString("") { if(it.startsWith("#")) "$it\n" else "# $it\n" }
            try {
                val startTime = System.currentTimeMillis()
                writeResponse(project, document, restFile, """
                    |$requestEntity
                    |# Start time:  ${LocalDateTime.now()}
                    |# Executing request...
                """.trimMargin())
                val response = executor!!.execute(requestModel)
                writeResponse(project, document, restFile, "$requestEntity # Reading response...")
                val headers = "\n# status " + response.status + "\n" + response.headersString()
                val text = getFormattedResponse(project, response.contentType, response.body)
                val duration = System.currentTimeMillis() - startTime
                writeResponse(project, document, restFile, """
                    |# Duration: $duration ms
                    |$requestEntity
                    |$headers$text
                    |""".trimMargin()
                )
            } catch (e1: Exception) {
                if (e1 !is IOException) {
                    e1.printStackTrace()
                }
                writeResponse(project, document, restFile, "$requestEntity\n# Error: " + e1.message)
            }
        }
    }


    private fun getRequestModel(restFile: RestFile): RequestModel {
        return ApplicationManager.getApplication().runReadAction<RequestModel> {
            RequestParser.parse(restFile.request)
        }
    }

    companion object {

        val ID = "rest.action.run"

        private fun writeResponse(project: Project, doc: Document, restFile: RestFile, text: String) {
            WriteCommandAction.runWriteCommandAction(project) {
                val responsePosition: Int
                val separatorString: String
                val separator = restFile.separator
                if (separator != null && separator.isValid) {
                    responsePosition = separator.textOffset
                    separatorString = ""
                } else {
                    val request = restFile.request
                    responsePosition = request!!.textOffset + request.textLength
                    separatorString = "\n"
                }
                val sb = "$separatorString%%%\n$text"
                doc.replaceString(responsePosition, doc.textLength, sb.replace("\r", ""))
            }
        }

        private fun getRestFile(project: Project, document: Document): RestFile? {
            return ApplicationManager.getApplication().runReadAction<RestFile?> a@{
                return@a if (project.isOpen) {
                    val psiFile = PsiDocumentManager.getInstance(project).getPsiFile(document)
                    if (psiFile != null && psiFile.isValid && psiFile is RestFile) {
                        psiFile
                    } else null
                } else null
            }
        }

    }
}