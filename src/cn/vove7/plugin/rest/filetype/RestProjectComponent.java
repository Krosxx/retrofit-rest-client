package cn.vove7.plugin.rest.filetype;

import com.intellij.openapi.components.AbstractProjectComponent;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileEditor.*;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.Disposer;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.openapi.vfs.VirtualFileAdapter;
import com.intellij.openapi.vfs.VirtualFileManager;
import com.intellij.openapi.vfs.VirtualFilePropertyEvent;
import com.intellij.openapi.vfs.impl.http.HttpVirtualFile;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiManager;
import com.intellij.util.messages.MessageBusConnection;

import org.jetbrains.annotations.NotNull;

/**
 * @author danblack
 */
public class RestProjectComponent extends AbstractProjectComponent {

    protected RestProjectComponent(Project project) {
        super(project);
    }

    @Override
    public void initComponent() {
        super.initComponent();
        registerOpenFileHandler(myProject.getMessageBus().connect(myProject));
        registerFileLanguageHandler();
    }

    private void registerFileLanguageHandler() {
        VirtualFileManager.getInstance().addVirtualFileListener(new VirtualFileAdapter() {
            @Override
            public void propertyChanged(@NotNull VirtualFilePropertyEvent event) {
                if (VirtualFile.PROP_NAME.equals(event.getPropertyName())) {
                    FileEditorManager fileEditorManager = FileEditorManager.getInstance(myProject);
                    VirtualFile file = event.getFile();
                    if (fileEditorManager.isFileOpen(file)) {
                        refreshComponent(fileEditorManager, file);
                    }
                }
            }
        }, myProject);
    }

    private void registerOpenFileHandler(MessageBusConnection connection) {
        connection.subscribe(FileEditorManagerListener.FILE_EDITOR_MANAGER, new FileEditorManagerAdapter() {
            @Override
            public void fileOpened(@NotNull FileEditorManager source, @NotNull VirtualFile file) {
                refreshComponent(source, file);
            }
        });
    }

    public static boolean isSuitable(@NotNull Project project, @NotNull VirtualFile file) {
        if (file instanceof HttpVirtualFile) {
            return false;
        }
        final FileViewProvider provider = PsiManager.getInstance(project).findViewProvider(file);
        return file.getName().endsWith("." + RestFileType.INSTANCE.getDefaultExtension())
                || provider != null && RestLanguage.INSTANCE == provider.getBaseLanguage();
    }


    private void detachComponent(@NotNull FileEditorManager fileEditorManager, @NotNull VirtualFile file) {
        for (FileEditor fileEditor : fileEditorManager.getAllEditors(file)) {
            if (fileEditor instanceof TextEditor) {
                HeaderPanelComponent.dispose(((TextEditor) fileEditor).getEditor());
            }
        }
    }

    private void refreshComponent(@NotNull final FileEditorManager fileEditorManager, @NotNull VirtualFile file) {
        if (isSuitable(fileEditorManager.getProject(), file)) {
            attachComponent(fileEditorManager, file);
        } else {
            detachComponent(fileEditorManager, file);
        }
    }

    private void attachComponent(@NotNull FileEditorManager fileEditorManager, @NotNull VirtualFile file) {
        for (FileEditor fileEditor : fileEditorManager.getAllEditors(file)) {
            if (fileEditor instanceof TextEditor) {
                TextEditor textEditor = (TextEditor) fileEditor;
                Editor editor = textEditor.getEditor();
                if (!HeaderPanelComponent.attached(editor)) {
                    HeaderPanelComponent component = new HeaderPanelComponent(textEditor);
                    Disposer.register(fileEditor, () -> Disposer.dispose(component));
                }
            }
        }
    }
}
