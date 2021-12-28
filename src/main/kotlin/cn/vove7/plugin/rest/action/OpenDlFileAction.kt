package cn.vove7.plugin.rest.action

import cn.vove7.plugin.rest.tool.RequestExecutor
import cn.vove7.plugin.rest.tool.open
import cn.vove7.plugin.rest.tool.virtualFile
import com.intellij.execution.configurations.UnknownConfigurationType
import com.intellij.execution.configurations.UnknownRunConfiguration
import com.intellij.icons.AllIcons
import com.intellij.ide.actions.RevealFileAction
import com.intellij.ide.browsers.JavaScriptDebuggerStarter
import com.intellij.ide.browsers.StartBrowserSettings
import com.intellij.ide.browsers.WebBrowserManager
import com.intellij.openapi.actionSystem.AnAction
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.fileEditor.TextEditor
import com.intellij.openapi.ui.Messages
import com.intellij.openapi.ui.popup.JBPopupFactory
import java.io.File


class OpenDlFileAction @JvmOverloads constructor(
        private val executor: RequestExecutor? = null,
        private val textEditor: TextEditor? = null
) : AnAction("Open download file", "", AllIcons.Nodes.PpWeb) {

    private val menuAction
        get() = listOf(
                "Open in Editor",
                "Open in Browser",
                "Show in Explorer"
        )

    override fun actionPerformed(p0: AnActionEvent) {
        val path = dlPath?.replace("\\", "/") ?: return
        val mas = menuAction
        JBPopupFactory.getInstance()
                .createPopupChooserBuilder<String>(mas)
                .setTitle("Open Action")
                .setItemChosenCallback {
                    when (menuAction.indexOf(it)) {
                        0 -> openInLocal(path)
                        1 -> openInBrowser(path)
                        2 -> showInExplorer(path)
                    }
                }.createPopup().showInCenterOf(p0.inputEvent.component)

    }

    private fun checkFile(path: String): File? {
        return File(path).let {
            if (it.exists()) {
                it
            } else {
                Messages.showMessageDialog(
                        textEditor?.editor?.project,
                        "File does not exist: $path",
                        "Error", Messages.getInformationIcon()
                )
                null
            }
        }
    }

    private fun showInExplorer(path: String) {
        val file = checkFile(path) ?: return
        RevealFileAction.openFile(file)
    }

    private fun openInBrowser(path: String) {
        checkFile(path) ?: return

        val browserSettings = StartBrowserSettings()
        browserSettings.isSelected = true
        browserSettings.browser = WebBrowserManager.getInstance().firstActiveBrowser
        browserSettings.isStartJavaScriptDebugger = false
        browserSettings.url = "file:///$path"

        JavaScriptDebuggerStarter.Util.startDebugOrLaunchBrowser(
                UnknownRunConfiguration(UnknownConfigurationType.getInstance(), textEditor!!.editor.project!!),
                browserSettings
        )

    }

    private fun openInLocal(path: String) {
        val file = checkFile(path) ?: return
        file.virtualFile()?.open(textEditor!!.editor.project!!)
    }

    private fun dlPath(): String? {
        val editor = textEditor?.editor ?: return null
        var path: String? = null
        editor.document.text.lines().filter { it.isNotEmpty() }.find {
            (it[0] == '#' && it.contains("file download complete")).also { b ->
                if (b) {
                    path = it.substring(26, it.length - 1)
                }
            }
        }
        return path
    }

    var dlPath: String? = null

    override fun update(e: AnActionEvent) {
        super.update(e)
        val path by lazy { dlPath() }
        dlPath = path
        WebBrowserManager.getInstance().firstActiveBrowser?.icon?.also {
            e.presentation.icon = it
        }
        e.presentation.isEnabled = executor?.isRunning == false && path != null
    }
}