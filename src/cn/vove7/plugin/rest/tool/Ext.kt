package cn.vove7.plugin.rest.tool

import cn.vove7.plugin.rest.action.RunAction
import com.intellij.lang.Language
import com.intellij.openapi.command.WriteCommandAction
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.fileEditor.OpenFileDescriptor
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.LocalFileSystem
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiNameValuePair
import com.intellij.psi.codeStyle.CodeStyleManager
import com.intellij.psi.impl.PsiFileFactoryImpl
import java.io.File
import java.net.URLDecoder


/**
 * # Ext
 * Created by 11324.
 * Date: 2019/9/27
 */

operator fun <K, V> Map<K, V>.get(k: K, dv: V): V {
    return getOrDefault(k, dv)
}

fun String.decodeUrl(): String = URLDecoder.decode(this)


val PsiElement.trimValue get() = text?.trim('"')

fun getFormattedResponse(project: Project, contentType: String?, text: String?): String {
    val langList = Language.findInstancesByMimeType(contentType)
    return if (!langList.isEmpty()) {
        format(project, langList.iterator().next(), text ?: "")
    } else text ?: ""
}

/**
 * 高亮/格式化代码
 * @param project Project
 * @param language Language
 * @param text String
 * @return String
 */
fun format(project: Project, language: Language, text: String): String {
    return WriteCommandAction.runWriteCommandAction<String>(project) {
        val psiFile: PsiFile = PsiFileFactoryImpl.getInstance(project).createFileFromText("virtual", language, text)
        CodeStyleManager.getInstance(project).reformatText(psiFile, 0, psiFile.textLength)
        psiFile.text
    }
}
fun <K, V> Map<K, V>.joinToString(
        separator: CharSequence = ", ",
        prefix: CharSequence = "[",
        postfix: CharSequence = "]",
        transform: (k: K, v: V) -> CharSequence = { k: K, v: V -> "$k: $v" }
): String {
    val map = this
    return buildString {
        var f = false
        append(prefix)
        map.forEach { (k, v) ->
            if (f) append(separator)
            append(transform(k, v))
            f = true
        }
        append(postfix)
    }

}

val PsiNameValuePair.trimValue get() = value?.text?.trim('"')

fun VirtualFile.open(project: Project) {
    FileEditorManager.getInstance(project)
            .openTextEditor(OpenFileDescriptor(project, this), true)
}

fun File.virtualFile(): VirtualFile? {
    return LocalFileSystem.getInstance().refreshAndFindFileByIoFile(this)
}

object arr {
    inline operator fun <reified T> get(vararg eles: T): Array<out T> = arrayOf(*eles)
}