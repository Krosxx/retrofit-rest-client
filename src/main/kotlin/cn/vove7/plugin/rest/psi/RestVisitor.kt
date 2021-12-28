// This is a generated file. Not intended for manual editing.
package cn.vove7.plugin.rest.psi

import com.intellij.psi.PsiElementVisitor
import cn.vove7.plugin.rest.psi.RestComments
import cn.vove7.plugin.rest.psi.RestEBad
import cn.vove7.plugin.rest.psi.RestEHeader
import cn.vove7.plugin.rest.psi.RestEMethod
import cn.vove7.plugin.rest.psi.RestEParam
import cn.vove7.plugin.rest.psi.RestESeparator
import cn.vove7.plugin.rest.psi.RestEUrl
import cn.vove7.plugin.rest.psi.RestHeaders
import cn.vove7.plugin.rest.psi.RestOptions
import cn.vove7.plugin.rest.psi.RestParams
import cn.vove7.plugin.rest.psi.RestRequest
import cn.vove7.plugin.rest.psi.RestRequestBody
import cn.vove7.plugin.rest.psi.RestResponse
import cn.vove7.plugin.rest.psi.RestResponseBody
import cn.vove7.plugin.rest.psi.RestWs
import com.intellij.psi.PsiElement

class RestVisitor : PsiElementVisitor() {
    fun visitComments(o: RestComments) {
        visitPsiElement(o)
    }

    fun visitEBad(o: RestEBad) {
        visitPsiElement(o)
    }

    fun visitEHeader(o: RestEHeader) {
        visitPsiElement(o)
    }

    fun visitEMethod(o: RestEMethod) {
        visitPsiElement(o)
    }

    fun visitEParam(o: RestEParam) {
        visitPsiElement(o)
    }

    fun visitESeparator(o: RestESeparator) {
        visitPsiElement(o)
    }

    fun visitEUrl(o: RestEUrl) {
        visitPsiElement(o)
    }

    fun visitHeaders(o: RestHeaders) {
        visitPsiElement(o)
    }

    fun visitOptions(o: RestOptions) {
        visitPsiElement(o)
    }

    fun visitParams(o: RestParams) {
        visitPsiElement(o)
    }

    fun visitRequest(o: RestRequest) {
        visitPsiElement(o)
    }

    fun visitRequestBody(o: RestRequestBody) {
        visitPsiElement(o)
    }

    fun visitResponse(o: RestResponse) {
        visitPsiElement(o)
    }

    fun visitResponseBody(o: RestResponseBody) {
        visitPsiElement(o)
    }

    fun visitWs(o: RestWs) {
        visitPsiElement(o)
    }

    fun visitPsiElement(o: PsiElement) {
        visitElement(o)
    }
}