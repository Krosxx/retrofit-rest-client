package cn.vove7.plugin.rest.filetype;

import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import cn.vove7.plugin.rest.psi.RestESeparator;
import cn.vove7.plugin.rest.psi.RestRequest;

/**
 *
 */
public class RestFile extends PsiFileBase {

    protected RestFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, RestLanguage.INSTANCE);
    }

    public RestRequest getRequest() {
        for (PsiElement element : getChildren()) {
            if (element instanceof RestRequest) {
                return (RestRequest) element;
            }
        }
        return null;
    }

    public RestESeparator getSeparator() {
        for (PsiElement element : getChildren()) {
            if (element instanceof RestESeparator) {
                return (RestESeparator) element;
            }
        }
        return null;
    }

    @NotNull
    @Override
    public FileType getFileType() {
        return RestFileType.INSTANCE;
    }
}
