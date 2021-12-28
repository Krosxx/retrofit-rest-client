package cn.vove7.plugin.rest.filetype

import cn.vove7.plugin.rest.filetype.HeaderPanelComponent.Companion.attached
import com.intellij.openapi.components.AbstractProjectComponent
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.FileEditorManagerAdapter
import com.intellij.openapi.fileEditor.FileEditorManagerListener
import com.intellij.openapi.fileEditor.TextEditor
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.vfs.VirtualFileAdapter
import com.intellij.openapi.vfs.VirtualFileManager
import com.intellij.openapi.vfs.VirtualFilePropertyEvent
import com.intellij.openapi.vfs.impl.http.HttpVirtualFile
import com.intellij.psi.PsiManager
import com.intellij.util.messages.MessageBusConnection

/**
 * @author danblack
 */
class RestProjectComponent protected constructor(project: Project?) : AbstractProjectComponent(project) {
    override fun initComponent() {
        super.initComponent()
        registerOpenFileHandler(myProject.messageBus.connect(myProject))
        registerFileLanguageHandler()
    }

    private fun registerFileLanguageHandler() {
        VirtualFileManager.getInstance().addVirtualFileListener(object : VirtualFileAdapter() {
            override fun propertyChanged(event: VirtualFilePropertyEvent) {
                if (VirtualFile.PROP_NAME == event.propertyName) {
                    val fileEditorManager = FileEditorManager.getInstance(myProject)
                    val file = event.file
                    if (fileEditorManager.isFileOpen(file)) {
                        refreshComponent(fileEditorManager, file)
                    }
                }
            }
        }, myProject)
    }

    private fun registerOpenFileHandler(connection: MessageBusConnection) {
        connection.subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, object : FileEditorManagerAdapter() {
            override fun fileOpened(source: FileEditorManager, file: VirtualFile) {
                refreshComponent(source, file)
            }
        })
    }

    private fun detachComponent(fileEditorManager: FileEditorManager, file: VirtualFile) {
        for (fileEditor in fileEditorManager.getAllEditors(file)) {
            if (fileEditor is TextEditor) {
                HeaderPanelComponent.dispose(fileEditor.editor)
            }
        }
    }

    private fun refreshComponent(fileEditorManager: FileEditorManager, file: VirtualFile) {
        if (isSuitable(fileEditorManager.project, file)) {
            attachComponent(fileEditorManager, file)
        } else {
            detachComponent(fileEditorManager, file)
        }
    }

    private fun attachComponent(fileEditorManager: FileEditorManager, file: VirtualFile) {
        for (fileEditor in fileEditorManager.getAllEditors(file)) {
            if (fileEditor is TextEditor) {
                val textEditor = fileEditor
                val editor = textEditor.editor
                if (!attached(editor)) {
                    val component = HeaderPanelComponent(textEditor)
                    Disposer.register(fileEditor) { Disposer.dispose(component) }
                }
            }
        }
    }

    companion object {
        fun isSuitable(project: Project, file: VirtualFile): Boolean {
            if (file is HttpVirtualFile) {
                return false
            }
            val provider = PsiManager.getInstance(project).findViewProvider(file)
            return (file.name.endsWith("." + RestFileType.INSTANCE.defaultExtension)
                    || provider != null && RestLanguage == provider.baseLanguage)
        }
    }
}