package cn.vove7.plugin.rest.filetype

import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import cn.vove7.plugin.rest.filetype.RestLexerAdapter
import com.intellij.psi.tree.IElementType
import com.intellij.openapi.editor.colors.TextAttributesKey
import cn.vove7.plugin.rest.psi.RestTypes
import cn.vove7.plugin.rest.filetype.RestHighlighter
import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.psi.TokenType

/**
 *
 */
class RestHighlighter : SyntaxHighlighterBase() {
    override fun getHighlightingLexer(): Lexer {
        return RestLexerAdapter()
    }

    override fun getTokenHighlights(tokenType: IElementType): Array<TextAttributesKey> {
        if (tokenType == RestTypes.SEPARATOR) {
            return SEPARATOR_KEYS
        } else if (tokenType == RestTypes.METHOD) {
            return METHOD_KEYS
        } else if (tokenType == RestTypes.URL) {
            return URL_KEYS
        } else if (tokenType == RestTypes.HEADER) {
            return HEADER_KEYS
        } else if (tokenType == RestTypes.OPTION) {
            return OPTION_KEYS
        } else if (tokenType == RestTypes.PARAM) {
            return PARAM_KEYS
        } else if (tokenType == RestTypes.COMMENT) {
            return COMMENT_KEYS
        } else if (tokenType == RestTypes.REQUEST_BODY_LINE || tokenType == RestTypes.RESPONSE_BODY_LINE) {
            return BODY_KEYS
        } else if (tokenType == TokenType.BAD_CHARACTER) {
            return BAD_CHAR_KEYS
        }
        return EMPTY
    }

    companion object {
        val SEPARATOR = TextAttributesKey.createTextAttributesKey("REST_SEPARATOR")
        val METHOD = TextAttributesKey.createTextAttributesKey("REST_METHOD")
        val URL = TextAttributesKey.createTextAttributesKey("REST_URL")
        val HEADER = TextAttributesKey.createTextAttributesKey("REST_HEADER")
        val OPTION = TextAttributesKey.createTextAttributesKey("REST_OPTION")
        val PARAM = TextAttributesKey.createTextAttributesKey("REST_PARAM")
        val COMMENT =
            TextAttributesKey.createTextAttributesKey("REST_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val BODY = TextAttributesKey.createTextAttributesKey("REST_BODY")
        val BAD_CHARACTER = TextAttributesKey.createTextAttributesKey("REST_BAD_CHARACTER")
        private val SEPARATOR_KEYS = arrayOf(SEPARATOR)
        private val METHOD_KEYS = arrayOf(METHOD)
        private val URL_KEYS = arrayOf(URL)
        private val HEADER_KEYS = arrayOf(HEADER)
        private val OPTION_KEYS = arrayOf(OPTION)
        private val PARAM_KEYS = arrayOf(PARAM)
        private val BAD_CHAR_KEYS = arrayOf(BAD_CHARACTER)
        private val COMMENT_KEYS = arrayOf(COMMENT)
        private val BODY_KEYS = arrayOf(BODY)
    }
}