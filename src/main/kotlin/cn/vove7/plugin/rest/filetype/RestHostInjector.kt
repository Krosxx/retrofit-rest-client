package cn.vove7.plugin.rest.filetype

import com.intellij.lang.injection.MultiHostInjector
import com.intellij.lang.injection.MultiHostRegistrar
import com.intellij.psi.PsiElement
import cn.vove7.plugin.rest.psi.RestHeaders
import cn.vove7.plugin.rest.psi.RestResponseBody
import cn.vove7.plugin.rest.psi.RestResponse
import cn.vove7.plugin.rest.psi.RestRequestBody
import cn.vove7.plugin.rest.psi.RestRequest
import com.intellij.psi.PsiLanguageInjectionHost
import com.intellij.openapi.util.TextRange
import cn.vove7.plugin.rest.psi.RestEHeader
import cn.vove7.plugin.rest.psi.impl.RestElementImpl
import com.intellij.lang.Language

/**
 *
 */
class RestHostInjector : MultiHostInjector {
    override fun getLanguagesToInject(registrar: MultiHostRegistrar, context: PsiElement) {
        var headers: RestHeaders? = null
        if (context is RestResponseBody) {
            headers = (context.getParent() as RestResponse).headers
        } else if (context is RestRequestBody) {
            headers = (context.getParent() as RestRequest).headers
        }
        //OOM  1 char 2 bytes  2M = 2<<20
        if (headers != null && context.textRange.length < 1 shl 20) {
            val contentType = getContentType(headers)
            if (contentType != null) {
                val langList = Language.findInstancesByMimeType(contentType)
                if (!langList.isEmpty()) {
                    registrar.startInjecting(langList.iterator().next())
                        .addPlace(
                            null,
                            null,
                            (context as PsiLanguageInjectionHost),
                            TextRange.create(0, context.getTextLength())
                        )
                        .doneInjecting()
                }
            }
        }
    }

    private fun isThunked(headers: RestHeaders): Boolean {
        return "chunked" == getHeader(headers, "Transfer-Encoding")
    }

    private fun getContentType(headers: RestHeaders): String? {
        return getHeader(headers, "Content-Type")
    }

    private fun getHeader(headers: RestHeaders, key: String): String? {
        if (headers.isValid) {
            for (eHeader in headers.eHeaderList) {
                val header = eHeader.text
                if (header.startsWith("@$key")) {
                    val colonIndex = header.indexOf(":")
                    if (colonIndex >= 0) {
                        val contentType = header.substring(colonIndex + 1)
                        val semicolonIndex = contentType.indexOf(";")
                        return if (semicolonIndex >= 0) {
                            contentType.substring(0, semicolonIndex).trim { it <= ' ' }
                        } else contentType.trim { it <= ' ' }
                    }
                }
            }
        }
        return null
    }

    override fun elementsToInjectIn(): List<Class<out PsiElement?>?> {
        return listOf(RestElementImpl::class.java)
    }
}