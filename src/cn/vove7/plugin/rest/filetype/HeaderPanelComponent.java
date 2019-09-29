package cn.vove7.plugin.rest.filetype;

import com.intellij.openapi.Disposable;
import com.intellij.openapi.actionSystem.ActionManager;
import com.intellij.openapi.actionSystem.ActionPlaces;
import com.intellij.openapi.actionSystem.CustomShortcutSet;
import com.intellij.openapi.actionSystem.DefaultActionGroup;
import com.intellij.openapi.actionSystem.impl.ActionToolbarImpl;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.fileEditor.TextEditor;
import com.intellij.openapi.keymap.Keymap;
import com.intellij.openapi.keymap.KeymapManager;
import com.intellij.openapi.keymap.KeymapManagerListener;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.util.Key;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Objects;

import cn.vove7.plugin.rest.action.RunAction;
import cn.vove7.plugin.rest.action.StopAction;
import cn.vove7.plugin.rest.tool.RequestExecutor;

/**
 * @author danblack
 */
public class HeaderPanelComponent implements Disposable {

    private static final Key<HeaderPanelComponent> HEADER_PANEL_KEY = new Key<>("HEADER_PANEL_KEY");

    public static void dispose(@NotNull Editor editor) {
        HeaderPanelComponent component = editor.getUserData(HEADER_PANEL_KEY);
        if (component != null) {
            Disposer.dispose(component);
        }
    }

    public static boolean attached(Editor editor) {
        return editor.getUserData(HEADER_PANEL_KEY) != null;
    }

    private final ActionToolbarImpl panel;
    private final RequestExecutor requestExecutor;
    private final RunAction runAction;
    private final StopAction stopAction;
    private final Editor editor;
    private TextEditor textEditor;
    private Keymap keymap;

    private final Keymap.Listener shortcutChangeListener = actionId -> {
        if (actionId.equals(RunAction.Companion.getID()) || actionId.equals(StopAction.Companion.getID())) {
            unregisterShortCuts();
            registerShortCuts();
        }
    };

    public HeaderPanelComponent(TextEditor textEditor) {
        this.textEditor = textEditor;
        editor = textEditor.getEditor();
        editor.putUserData(HEADER_PANEL_KEY, this);

        requestExecutor = new RequestExecutor();

        runAction = new RunAction(requestExecutor, textEditor);
        stopAction = new StopAction(requestExecutor);

        panel = createPanel(createActionGroup());
        getFileEditorManager().addTopComponent(textEditor, panel);

        registerKeymapManagerListener();
        registerShortCuts();
    }

    @Override
    public void dispose() {
        if (textEditor != null) {
            getFileEditorManager().removeTopComponent(textEditor, panel);
            unregisterShortCuts();
            editor.putUserData(HEADER_PANEL_KEY, null);
            textEditor = null;
        }
    }

    private FileEditorManager getFileEditorManager() {
        return FileEditorManager.getInstance(editor.getProject());
    }

    @NotNull
    private DefaultActionGroup createActionGroup() {
        DefaultActionGroup group = new DefaultActionGroup("rest-request", false);
        group.add(runAction);
        group.add(stopAction);
        return group;
    }

    private ActionToolbarImpl createPanel(DefaultActionGroup group) {
        ActionToolbarImpl result = (ActionToolbarImpl) ActionManager.getInstance().createActionToolbar(ActionPlaces.EDITOR_TOOLBAR, group, true);
        result.setForceMinimumSize(true);
        return result;
    }

    private void registerShortCuts() {
        Objects.requireNonNull(editor, "Editor required");

        KeymapManager keymapManager = KeymapManager.getInstance();
        Keymap newKeymap = keymapManager.getActiveKeymap();

        if (!Objects.equals(keymap, newKeymap)) {
            if (keymap != null) {
                unregisterShortCuts();
                keymap.removeShortcutChangeListener(shortcutChangeListener);
            }
            keymap = newKeymap;
            keymap.addShortcutChangeListener(shortcutChangeListener); // does not work
        }

        runAction.registerCustomShortcutSet(
                new CustomShortcutSet(keymap.getShortcuts(RunAction.Companion.getID())),
                editor.getComponent());

        stopAction.registerCustomShortcutSet(
                new CustomShortcutSet(keymap.getShortcuts(StopAction.Companion.getID())),
                editor.getComponent());
    }

    private void registerKeymapManagerListener() {
        KeymapManager keymapManager = KeymapManager.getInstance();
        keymapManager.addKeymapManagerListener(new KeymapManagerListener(){
            @Override
            public void activeKeymapChanged(@Nullable Keymap keymap) {
                unregisterShortCuts();
                registerShortCuts();
            }
        }, this);
    }

    private void unregisterShortCuts() {
        Objects.requireNonNull(editor, "Editor required");
        runAction.unregisterCustomShortcutSet(editor.getComponent());
        stopAction.unregisterCustomShortcutSet(editor.getComponent());
    }
}
