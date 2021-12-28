package cn.vove7.plugin.rest.filetype

import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import cn.vove7.plugin.rest.filetype.RestHighlighter
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.project.Project

/**
 *
 */
class RestHighlighterFactory : SyntaxHighlighterFactory() {
    private val restHighlighter = RestHighlighter()
    override fun getSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter {
        return restHighlighter
    }
}