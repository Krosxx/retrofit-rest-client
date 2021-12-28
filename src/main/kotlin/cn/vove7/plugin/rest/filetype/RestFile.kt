package cn.vove7.plugin.rest.filetype

import com.intellij.psi.FileViewProvider
import com.intellij.extapi.psi.PsiFileBase
import cn.vove7.plugin.rest.filetype.RestLanguage
import cn.vove7.plugin.rest.psi.RestRequest
import com.intellij.psi.PsiElement
import cn.vove7.plugin.rest.psi.RestESeparator
import cn.vove7.plugin.rest.filetype.RestFileType
import com.intellij.openapi.fileTypes.FileType

/**
 *
 */
class RestFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, RestLanguage) {
    val request: RestRequest?
        get() {
            for (element in children) {
                if (element is RestRequest) {
                    return element
                }
            }
            return null
        }
    val separator: RestESeparator?
        get() {
            for (element in children) {
                if (element is RestESeparator) {
                    return element
                }
            }
            return null
        }

    override fun getFileType(): FileType {
        return RestFileType.INSTANCE
    }
}