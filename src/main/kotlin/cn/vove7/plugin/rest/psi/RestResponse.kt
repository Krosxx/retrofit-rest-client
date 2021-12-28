// This is a generated file. Not intended for manual editing.
package cn.vove7.plugin.rest.psi

import com.intellij.psi.PsiElement
import cn.vove7.plugin.rest.psi.RestComments
import cn.vove7.plugin.rest.psi.RestHeaders
import cn.vove7.plugin.rest.psi.RestResponseBody

interface RestResponse : PsiElement {
    val commentsList: List<RestComments?>
    val headers: RestHeaders?
    val responseBody: RestResponseBody?
}