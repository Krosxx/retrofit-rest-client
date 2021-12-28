// This is a generated file. Not intended for manual editing.
package cn.vove7.plugin.rest.psi

import com.intellij.psi.PsiElement
import cn.vove7.plugin.rest.psi.RestComments
import cn.vove7.plugin.rest.psi.RestEMethod
import cn.vove7.plugin.rest.psi.RestEUrl
import cn.vove7.plugin.rest.psi.RestHeaders
import cn.vove7.plugin.rest.psi.RestOptions
import cn.vove7.plugin.rest.psi.RestParams
import cn.vove7.plugin.rest.psi.RestRequestBody
import cn.vove7.plugin.rest.psi.RestWs

interface RestRequest : PsiElement {
    val commentsList: List<RestComments?>
    val eMethod: RestEMethod?
    val eUrl: RestEUrl
    val headers: RestHeaders?
    val options: RestOptions?
    val params: RestParams?
    val requestBody: RestRequestBody?
    val wsList: List<RestWs?>
}