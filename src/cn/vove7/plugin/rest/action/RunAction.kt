package cn.vove7.plugin.rest.action

import cn.vove7.plugin.rest.filetype.RequestParser
import cn.vove7.plugin.rest.filetype.RestFile
import cn.vove7.plugin.rest.filetype.RestLanguage
import cn.vove7.plugin.rest.model.RequestModel
import cn.vove7.plugin.rest.tool.*
import com.google.gson.JsonObject
import com.intellij.icons.AllIcons
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
import okhttp3.ResponseBody
import java.awt.EventQueue
import java.io.BufferedOutputStream
import java.io.File
import java.io.StringWriter
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

        var restFile = getRestFile(project, document)

        //file is to large
        if (restFile == null) {
            val docText = document.text
            val separatorIndex = docText.indexOf("%%%")
            if (separatorIndex > 1) {
                restFile = createTmpRestFile(project, docText[0, separatorIndex - 1])
            }
        }

        restFile ?: return

        val requestModel = getRequestModel(restFile)

        val run = fun(env: JsonObject?) {
            runWithConfig(env, project, requestModel, document, restFile)
        }

        if (requestModel.hasUnfilledParam()) {
            val envConfig = EnvConfig(project)
            if (envConfig.hasConfig()) {
                try {
                    showEnvPopup(envConfig) { run(envConfig.getEnv(it)) }
                } catch (e: Exception) {
                    writeResponse(project, document, restFile, "# Error: " + e.message)
                }
            } else run(null)
        } else {
            run(null)
        }
    }

    private fun showEnvPopup(envConfig: EnvConfig, onChosen: (name: String) -> Unit) {
        val editor = textEditor?.editor ?: return

        val envs = envConfig.allEnv()
        if (envs.size == 1) {
            onChosen(envs[0])
            return
        }
        val envWithUrl = envs.map {
            it.toUpperCase() + " " + envConfig.getEnv(it)["BASE_URL", ""]
        }
        JBPopupFactory.getInstance().createPopupChooserBuilder<String>(envWithUrl).setTitle("Choose env").setItemChosenCallback {
            onChosen.invoke(envs[envWithUrl.indexOf(it)])
        }.createPopup().show(RelativePoint.getNorthWestOf(editor.component))
    }

    operator fun JsonObject?.get(k: String, dv: String): String = this?.get(k)?.asString ?: dv

    private fun runWithConfig(
            env: JsonObject?,
            project: Project,
            requestModel: RequestModel,
            document: Document,
            restFile: RestFile
    ) {
        ApplicationManager.getApplication().executeOnPooledThread a@{
            if (env != null) {//requestModel.hasUnfilledParam() == true
                if ("headers" in env) {
                    requestModel.headers.putAll((env.get("headers") as JsonObject).toStringMap().filter { it.key !in requestModel.headers })
                }
                env.remove("headers")
                requestModel.fill(env)
            }

            val requestEntity = requestModel.toRestFileContent(project).lines().filter { it.isNotEmpty() }.joinToString("") { if (it.startsWith("#")) "$it\n" else "# $it\n" }

            var targetFileLazy: Lazy<File>? = null
            try {
                val startTime = System.currentTimeMillis()
                writeResponse(project, document, restFile, """
                    |$requestEntity
                    |# Start time:  ${LocalDateTime.now()}
                    |# Executing request...
                """.trimMargin())
                val response = executor!!.execute(requestModel)
                val headers = "\n# status " + response.status + "\n" + response.headersString()

                val cl = response.body?.contentLength() ?: 0L

                targetFileLazy = lazy {
                    val dlDir = File(project.basePath, ".idea/rest-client/download/")
                    if (!dlDir.exists()) {
                        dlDir.mkdirs()
                    }
                    val urlName by lazy {
                        requestModel.url.let { it[it.lastIndexOf('/') + 1, 0] }
                                .let {
                                    if (!it.contains('?', false)) it
                                    else it[0, it.lastIndexOf('?')]
                                }
                    }

                    val rp by lazy { response.getFilePostfix() }
                    val name = response.fileName ?: if (!urlName.endsWith(rp, true)) {
                        urlName + rp
                    } else {
                        urlName
                    }

                    File(dlDir, name).also { f ->
                        if (f.exists()) {
                            f.delete()
                        }
                        f.createNewFile()
                        f.virtualFile()?.refresh(true, false)
                    }
                }

                if (response.isUnText()) {//> 3M
                    //download file to {PROJECT_DIR}/.idea/rest-client/download/{fileName}
                    val targetFile by targetFileLazy

                    writeResponse(project, document, restFile,
                            """$requestEntity 
                                |# Response is too large
                                |# downloading to [${targetFile.absolutePath}]...
                                |# [${getProgressText(cl, 0)}]""".trimMargin())

                    var lastNotify = 0L
                    BufferedOutputStream(targetFile.outputStream()).use { os ->
                        response.body!!.readData { bs, len, readLength ->
                            os.write(bs, 0, len)
                            val now = System.currentTimeMillis()
                            if (now - lastNotify > 800) {
                                lastNotify = now
                                writeResponse(project, document, restFile,
                                        """$requestEntity
                                        |$headers
                                        |# Response is too large
                                        |# downloading to [${targetFile.absolutePath}]...
                                        |# [${getProgressText(cl, readLength)}]""".trimMargin())
                            }
                        }
                    }
                    val duration = System.currentTimeMillis() - startTime
                    writeResponse(project, document, restFile,
                            """$requestEntity 
                                |$headers
                                |# Duration: $duration ms
                                |# Response is too large
                                |# file download complete [${targetFile.absolutePath}]
                                |# [${"#" * 50}]""".trimMargin())
                    if (Thread.currentThread().isInterrupted) return@a
                    EventQueue.invokeLater {
                        targetFile.virtualFile()?.apply {
                            refresh(true, false)
                            open(project)
                        }
                    }
                } else {
                    writeResponse(project, document, restFile, "$requestEntity " +
                            "# Reading response...")

                    if (cl shr 20 > 2 || response.isChunked) {
                        val sw = StringWriter(100)

                        var lastNotify = 0L
                        var tl = 0
                        var largeContent = false

                        val tarFile by targetFileLazy

                        response.body?.readData { bs, len, readLength ->
                            tl = readLength
                            val now = System.currentTimeMillis()
                            if (!largeContent && readLength > 1 shl 20) {
                                largeContent = true
                                tarFile.writeText(sw.toString())
                                tarFile.appendText(String(bs, 0, len))
                            } else if (largeContent) {
                                tarFile.appendText(String(bs, 0, len))
                            } else {
                                sw.write(String(bs, 0, len))
                                sw.flush()
                            }
                            if (now - lastNotify > 1000) {
                                lastNotify = now

                                writeResponse(project, document, restFile,
                                        """$requestEntity
                                        |$headers
                                        |${if (!largeContent) ""
                                        else "# Content is too long\n# file download to [${tarFile.absolutePath}]"}
                                        |# length: [${getProgressText(cl, readLength)}...]
                                        |${if (largeContent) "" else sw.toString()}
                                        |""".trimMargin())
                            }
                        }
                        val duration = System.currentTimeMillis() - startTime
                        if (!largeContent) {
                            writeResponse(project, document, restFile,
                                    """
                                |# Duration: $duration ms
                                |$requestEntity
                                |$headers
                                |# length: [$tl]
                                |${getFormattedResponse(project, response.contentType, sw.toString().decodeUnicode())}
                                |""".trimMargin()
                            )
                        } else {
                            writeResponse(project, document, restFile,
                                    """
                                |# Duration: $duration ms
                                |$requestEntity
                                |$headers
                                |# Content is too long
                                |# file download complete [${tarFile.absolutePath}]
                                |# length: [$tl]
                                |""".trimMargin()
                            )
                            EventQueue.invokeLater {
                                tarFile.virtualFile()?.apply {
                                    refresh(true, false)
                                    open(project)
                                }
                            }
                        }
                    } else {
                        val duration = System.currentTimeMillis() - startTime
                        writeResponse(project, document, restFile,
                                """
                                |# Duration: $duration ms
                                |$requestEntity
                                |$headers${getFormattedResponse(project,
                                        response.contentType,
                                        response.body?.string()?.decodeUnicode())}
                                |""".trimMargin()
                        )
                    }

                }
            } catch (e1: Exception) {
                e1.printStackTrace()
                if (targetFileLazy?.isInitialized() == true) {
                    targetFileLazy.value.delete()
                    targetFileLazy.value.parentFile.virtualFile()?.refresh(true, false)
                }
                writeResponse(project, document, restFile, "$requestEntity\n# Error: $e1")
            } finally {
                executor!!.state = RequestExecutor.State.WAITING
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

    private fun ResponseBody.readData(onRead: (ByteArray, len: Int, readLength: Int) -> Unit) {
        source().inputStream().use { src ->
            val bs = ByteArray(1024)
            var len: Int
            var readLength = 0
            while (src.read(bs).also { len = it } > 0) {
                readLength += len
                onRead(bs, len, readLength)
            }
        }
    }

    private fun createTmpRestFile(project: Project, requestContent: String): RestFile {
        return WriteCommandAction.runWriteCommandAction<RestFile>(project) {
            val psiFile: PsiFile = PsiFileFactoryImpl.getInstance(project)
                    .createFileFromText("virtual", RestLanguage.INSTANCE, requestContent)
            CodeStyleManager.getInstance(project).reformatText(psiFile, 0, requestContent.length)
            psiFile as RestFile
        }
    }

    private fun getProgressText(cl: Long, readLength: Int): String {
        return if (cl > 0L) {
            val progress = readLength.toDouble() / cl
            val p = (50 * progress).toInt()
            "#" * p + ("." * (50 - p))
        } else {
            readLength.toString()
        }
    }
}

