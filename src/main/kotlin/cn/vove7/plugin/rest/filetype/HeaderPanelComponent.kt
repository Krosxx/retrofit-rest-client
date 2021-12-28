package cn.vove7.plugin.rest.filetype

import cn.vove7.plugin.rest.action.OpenDlFileAction
import cn.vove7.plugin.rest.action.RunAction
import cn.vove7.plugin.rest.action.StopAction
import cn.vove7.plugin.rest.tool.RequestExecutor
import com.intellij.openapi.Disposable
import com.intellij.openapi.actionSystem.ActionManager
import com.intellij.openapi.actionSystem.ActionPlaces
import com.intellij.openapi.actionSystem.CustomShortcutSet
import com.intellij.openapi.actionSystem.DefaultActionGroup
import com.intellij.openapi.actionSystem.impl.ActionToolbarImpl
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.TextEditor
import com.intellij.openapi.keymap.Keymap
import com.intellij.openapi.keymap.KeymapManager
import com.intellij.openapi.keymap.KeymapManagerListener
import com.intellij.openapi.util.Disposer
import com.intellij.openapi.util.Key
import java.util.*

/**
 * @author danblack
 */
class HeaderPanelComponent(textEditor: TextEditor) : Disposable {
    private val panel: ActionToolbarImpl
    private val runAction: RunAction
    private val stopAction: StopAction
    private val openDlFileAction: OpenDlFileAction
    private val editor: Editor
    private var textEditor: TextEditor?
    private var keymap: Keymap? = null
    private val shortcutChangeListener = object : KeymapManagerListener {

        override fun shortcutChanged(keymap: Keymap, actionId: String) {
            if (actionId == RunAction.ID || actionId == StopAction.ID) {
                unregisterShortCuts()
//                registerShortCuts()
            }
        }
    }

    override fun dispose() {
        if (textEditor != null) {
            fileEditorManager.removeTopComponent(textEditor!!, panel)
            unregisterShortCuts()
            editor.putUserData(HEADER_PANEL_KEY, null)
            textEditor = null
        }
    }

    private val fileEditorManager: FileEditorManager
        private get() = FileEditorManager.getInstance(editor.project!!)

    private fun createActionGroup(): DefaultActionGroup {
        val group = DefaultActionGroup("rest-request", false)
        group.add(runAction)
        group.add(stopAction)
        group.add(openDlFileAction)
        return group
    }

    private fun createPanel(group: DefaultActionGroup): ActionToolbarImpl {
        val result = ActionManager.getInstance()
            .createActionToolbar(ActionPlaces.EDITOR_TOOLBAR, group, true) as ActionToolbarImpl
        result.setForceMinimumSize(true)
        return result
    }

//    private fun registerShortCuts() {
//        Objects.requireNonNull(editor, "Editor required")
//        val keymapManager = KeymapManager.getInstance()
//        val newKeymap = keymapManager.activeKeymap
//        if (keymap != newKeymap) {
//            if (keymap != null) {
//                unregisterShortCuts()
//                keymap!!.removeShortcutChangeListener(shortcutChangeListener)
//            }
//            keymap = newKeymap
//            keymap!!.addShortcutChangeListener(shortcutChangeListener) // does not work
//        }
//        runAction.registerCustomShortcutSet(
//            CustomShortcutSet(*keymap!!.getShortcuts(RunAction.ID)),
//            editor.component
//        )
//        stopAction.registerCustomShortcutSet(
//            CustomShortcutSet(*keymap!!.getShortcuts(StopAction.ID)),
//            editor.component
//        )
//    }

//    private fun registerKeymapManagerListener() {
//        val keymapManager = KeymapManager.getInstance()
//        keymapManager.addKeymapManagerListener(object : KeymapManagerListener {
//            override fun activeKeymapChanged(keymap: Keymap?) {
//                unregisterShortCuts()
//                registerShortCuts()
//            }
//        }, this)
//    }

    private fun unregisterShortCuts() {
        Objects.requireNonNull(editor, "Editor required")
        runAction.unregisterCustomShortcutSet(editor.component)
        stopAction.unregisterCustomShortcutSet(editor.component)
    }

    companion object {
        private val HEADER_PANEL_KEY = Key<HeaderPanelComponent>("HEADER_PANEL_KEY")
        fun dispose(editor: Editor) {
            val component = editor.getUserData(HEADER_PANEL_KEY)
            if (component != null) {
                Disposer.dispose(component)
            }
        }

        @JvmStatic
        fun attached(editor: Editor): Boolean {
            return editor.getUserData(HEADER_PANEL_KEY) != null
        }
    }

    init {
        this.textEditor = textEditor
        editor = textEditor.editor
        editor.putUserData(HEADER_PANEL_KEY, this)
        val requestExecutor = RequestExecutor()
        runAction = RunAction(requestExecutor, textEditor)
        stopAction = StopAction(requestExecutor)
        openDlFileAction = OpenDlFileAction(requestExecutor, textEditor)
        panel = createPanel(createActionGroup())
        fileEditorManager.addTopComponent(textEditor, panel)
//        registerKeymapManagerListener()
//        registerShortCuts()
    }
}