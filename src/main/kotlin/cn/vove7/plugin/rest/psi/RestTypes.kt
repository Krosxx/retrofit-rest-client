// This is a generated file. Not intended for manual editing.
package cn.vove7.plugin.rest.psi

import com.intellij.psi.PsiElement
import com.intellij.psi.tree.IElementType
import cn.vove7.plugin.rest.psi.RestTypes
import cn.vove7.plugin.rest.psi.impl.RestCommentsImpl
import cn.vove7.plugin.rest.psi.impl.RestEBadImpl
import cn.vove7.plugin.rest.psi.impl.RestEHeaderImpl
import cn.vove7.plugin.rest.psi.impl.RestEMethodImpl
import cn.vove7.plugin.rest.psi.impl.RestEParamImpl
import cn.vove7.plugin.rest.psi.impl.RestESeparatorImpl
import cn.vove7.plugin.rest.psi.impl.RestEUrlImpl
import cn.vove7.plugin.rest.psi.impl.RestHeadersImpl
import cn.vove7.plugin.rest.psi.impl.RestOptionsImpl
import cn.vove7.plugin.rest.psi.impl.RestParamsImpl
import cn.vove7.plugin.rest.psi.impl.RestRequestImpl
import cn.vove7.plugin.rest.psi.impl.RestRequestBodyImpl
import cn.vove7.plugin.rest.psi.impl.RestResponseImpl
import cn.vove7.plugin.rest.psi.impl.RestResponseBodyImpl
import cn.vove7.plugin.rest.psi.impl.RestWsImpl
import java.lang.AssertionError
import cn.vove7.plugin.rest.psi.RestElementType
import cn.vove7.plugin.rest.psi.RestTokenType
import com.intellij.lang.ASTNode

interface RestTypes {
    object Factory {
        fun createElement(node: ASTNode): PsiElement {
            val type = node.elementType
            if (type === COMMENTS) {
                return RestCommentsImpl(node)
            } else if (type === E_BAD) {
                return RestEBadImpl(node)
            } else if (type === E_HEADER) {
                return RestEHeaderImpl(node)
            } else if (type === E_METHOD) {
                return RestEMethodImpl(node)
            } else if (type === E_PARAM) {
                return RestEParamImpl(node)
            } else if (type === E_SEPARATOR) {
                return RestESeparatorImpl(node)
            } else if (type === E_URL) {
                return RestEUrlImpl(node)
            } else if (type === HEADERS) {
                return RestHeadersImpl(node)
            } else if (type === OPTIONS) {
                return RestOptionsImpl(node)
            } else if (type === PARAMS) {
                return RestParamsImpl(node)
            } else if (type === REQUEST) {
                return RestRequestImpl(node)
            } else if (type === REQUEST_BODY) {
                return RestRequestBodyImpl(node)
            } else if (type === RESPONSE) {
                return RestResponseImpl(node)
            } else if (type === RESPONSE_BODY) {
                return RestResponseBodyImpl(node)
            } else if (type === WS) {
                return RestWsImpl(node)
            }
            throw AssertionError("Unknown element type: $type")
        }
    }

    companion object {
        val COMMENTS: IElementType = RestElementType("COMMENTS")
        val E_BAD: IElementType = RestElementType("E_BAD")
        val E_HEADER: IElementType = RestElementType("E_HEADER")
        val E_METHOD: IElementType = RestElementType("E_METHOD")
        val E_PARAM: IElementType = RestElementType("E_PARAM")
        val E_SEPARATOR: IElementType = RestElementType("E_SEPARATOR")
        val E_URL: IElementType = RestElementType("E_URL")
        val HEADERS: IElementType = RestElementType("HEADERS")
        val OPTIONS: IElementType = RestElementType("OPTIONS")
        val PARAMS: IElementType = RestElementType("PARAMS")
        val REQUEST: IElementType = RestElementType("REQUEST")
        val REQUEST_BODY: IElementType = RestElementType("REQUEST_BODY")
        val RESPONSE: IElementType = RestElementType("RESPONSE")
        val RESPONSE_BODY: IElementType = RestElementType("RESPONSE_BODY")
        val WS: IElementType = RestElementType("WS")
        val BAD_CHARACTER: IElementType = RestTokenType("BAD_CHARACTER")
        val COMMENT: IElementType = RestTokenType("COMMENT")
        val CRLF: IElementType = RestTokenType("CRLF")
        val HEADER: IElementType = RestTokenType("HEADER")
        val METHOD: IElementType = RestTokenType("METHOD")
        val OPTION: IElementType = RestTokenType("OPTION")
        val PARAM: IElementType = RestTokenType("PARAM")
        val REQUEST_BODY_LINE: IElementType = RestTokenType("REQUEST_BODY_LINE")
        val RESPONSE_BODY_LINE: IElementType = RestTokenType("RESPONSE_BODY_LINE")
        val SEPARATOR: IElementType = RestTokenType("SEPARATOR")
        val URL: IElementType = RestTokenType("URL")
        val WHITE_SPACE: IElementType = RestTokenType("WHITE_SPACE")
    }
}