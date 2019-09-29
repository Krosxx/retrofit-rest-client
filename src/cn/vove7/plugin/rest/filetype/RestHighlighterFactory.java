package cn.vove7.plugin.rest.filetype;

import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.vfs.VirtualFile;
import org.jetbrains.annotations.NotNull;

/**
 *
 */
public class RestHighlighterFactory extends SyntaxHighlighterFactory {

    private final RestHighlighter restHighlighter = new RestHighlighter();

    @NotNull
    @Override
    public SyntaxHighlighter getSyntaxHighlighter(Project project, VirtualFile virtualFile) {
        return restHighlighter;
    }
}
