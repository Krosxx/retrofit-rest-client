// This is a generated file. Not intended for manual editing.
package cn.vove7.plugin.rest.parser

import com.intellij.lang.PsiParser
import com.intellij.lang.LightPsiParser
import com.intellij.psi.tree.IElementType
import com.intellij.lang.PsiBuilder
import com.intellij.lang.parser.GeneratedParserUtilBase
import cn.vove7.plugin.rest.parser.RestParser
import cn.vove7.plugin.rest.psi.RestTypes
import com.intellij.lang.ASTNode

class RestParser : PsiParser, LightPsiParser {
    override fun parse(t: IElementType, b: PsiBuilder): ASTNode {
        parseLight(t, b)
        return b.treeBuilt
    }

    override fun parseLight(t: IElementType, b: PsiBuilder) {
        var b: PsiBuilder? = b
        val r: Boolean
        b = GeneratedParserUtilBase.adapt_builder_(t, b, this, null)
        val m = GeneratedParserUtilBase.enter_section_(b, 0, GeneratedParserUtilBase._COLLAPSE_, null)
        r = parse_root_(t, b)
        GeneratedParserUtilBase.exit_section_(b, 0, m, t, r, true, GeneratedParserUtilBase.TRUE_CONDITION)
    }

    protected fun parse_root_(t: IElementType?, b: PsiBuilder?): Boolean {
        return parse_root_(t, b, 0)
    }

    companion object {
        fun parse_root_(t: IElementType?, b: PsiBuilder?, l: Int): Boolean {
            return restFile(b, l + 1)
        }

        /* ********************************************************** */ // COMMENT {COMMENT|ws}*
        fun comments(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "comments")) return false
            if (!GeneratedParserUtilBase.nextTokenIs(b, RestTypes.COMMENT)) return false
            var r: Boolean
            val m = GeneratedParserUtilBase.enter_section_(b)
            r = GeneratedParserUtilBase.consumeToken(b, RestTypes.COMMENT)
            r = r && comments_1(b, l + 1)
            GeneratedParserUtilBase.exit_section_(b, m, RestTypes.COMMENTS, r)
            return r
        }

        // {COMMENT|ws}*
        private fun comments_1(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "comments_1")) return false
            while (true) {
                val c = GeneratedParserUtilBase.current_position_(b)
                if (!comments_1_0(b, l + 1)) break
                if (!GeneratedParserUtilBase.empty_element_parsed_guard_(b, "comments_1", c)) break
            }
            return true
        }

        // COMMENT|ws
        private fun comments_1_0(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "comments_1_0")) return false
            var r: Boolean
            r = GeneratedParserUtilBase.consumeToken(b, RestTypes.COMMENT)
            if (!r) r = ws(b, l + 1)
            return r
        }

        /* ********************************************************** */ // BAD_CHARACTER
        fun e_bad(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "e_bad")) return false
            if (!GeneratedParserUtilBase.nextTokenIs(b, RestTypes.BAD_CHARACTER)) return false
            val r: Boolean
            val m = GeneratedParserUtilBase.enter_section_(b)
            r = GeneratedParserUtilBase.consumeToken(b, RestTypes.BAD_CHARACTER)
            GeneratedParserUtilBase.exit_section_(b, m, RestTypes.E_BAD, r)
            return r
        }

        /* ********************************************************** */ // HEADER
        fun e_header(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "e_header")) return false
            if (!GeneratedParserUtilBase.nextTokenIs(b, RestTypes.HEADER)) return false
            val r: Boolean
            val m = GeneratedParserUtilBase.enter_section_(b)
            r = GeneratedParserUtilBase.consumeToken(b, RestTypes.HEADER)
            GeneratedParserUtilBase.exit_section_(b, m, RestTypes.E_HEADER, r)
            return r
        }

        /* ********************************************************** */ // METHOD
        fun e_method(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "e_method")) return false
            if (!GeneratedParserUtilBase.nextTokenIs(b, RestTypes.METHOD)) return false
            val r: Boolean
            val m = GeneratedParserUtilBase.enter_section_(b)
            r = GeneratedParserUtilBase.consumeToken(b, RestTypes.METHOD)
            GeneratedParserUtilBase.exit_section_(b, m, RestTypes.E_METHOD, r)
            return r
        }

        /* ********************************************************** */ // PARAM
        fun e_param(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "e_param")) return false
            if (!GeneratedParserUtilBase.nextTokenIs(b, RestTypes.PARAM)) return false
            val r: Boolean
            val m = GeneratedParserUtilBase.enter_section_(b)
            r = GeneratedParserUtilBase.consumeToken(b, RestTypes.PARAM)
            GeneratedParserUtilBase.exit_section_(b, m, RestTypes.E_PARAM, r)
            return r
        }

        /* ********************************************************** */ // SEPARATOR
        fun e_separator(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "e_separator")) return false
            if (!GeneratedParserUtilBase.nextTokenIs(b, RestTypes.SEPARATOR)) return false
            val r: Boolean
            val m = GeneratedParserUtilBase.enter_section_(b)
            r = GeneratedParserUtilBase.consumeToken(b, RestTypes.SEPARATOR)
            GeneratedParserUtilBase.exit_section_(b, m, RestTypes.E_SEPARATOR, r)
            return r
        }

        /* ********************************************************** */ // URL
        fun e_url(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "e_url")) return false
            if (!GeneratedParserUtilBase.nextTokenIs(b, RestTypes.URL)) return false
            val r: Boolean
            val m = GeneratedParserUtilBase.enter_section_(b)
            r = GeneratedParserUtilBase.consumeToken(b, RestTypes.URL)
            GeneratedParserUtilBase.exit_section_(b, m, RestTypes.E_URL, r)
            return r
        }

        /* ********************************************************** */ // e_header {e_header|COMMENT|ws}*
        fun headers(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "headers")) return false
            if (!GeneratedParserUtilBase.nextTokenIs(b, RestTypes.HEADER)) return false
            var r: Boolean
            val m = GeneratedParserUtilBase.enter_section_(b)
            r = e_header(b, l + 1)
            r = r && headers_1(b, l + 1)
            GeneratedParserUtilBase.exit_section_(b, m, RestTypes.HEADERS, r)
            return r
        }

        // {e_header|COMMENT|ws}*
        private fun headers_1(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "headers_1")) return false
            while (true) {
                val c = GeneratedParserUtilBase.current_position_(b)
                if (!headers_1_0(b, l + 1)) break
                if (!GeneratedParserUtilBase.empty_element_parsed_guard_(b, "headers_1", c)) break
            }
            return true
        }

        // e_header|COMMENT|ws
        private fun headers_1_0(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "headers_1_0")) return false
            var r: Boolean
            r = e_header(b, l + 1)
            if (!r) r = GeneratedParserUtilBase.consumeToken(b, RestTypes.COMMENT)
            if (!r) r = ws(b, l + 1)
            return r
        }

        /* ********************************************************** */ // request e_separator? ws* response?
        fun item_(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "item_")) return false
            var r: Boolean
            val m = GeneratedParserUtilBase.enter_section_(b)
            r = request(b, l + 1)
            r = r && item__1(b, l + 1)
            r = r && item__2(b, l + 1)
            r = r && item__3(b, l + 1)
            GeneratedParserUtilBase.exit_section_(b, m, null, r)
            return r
        }

        // e_separator?
        private fun item__1(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "item__1")) return false
            e_separator(b, l + 1)
            return true
        }

        // ws*
        private fun item__2(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "item__2")) return false
            while (true) {
                val c = GeneratedParserUtilBase.current_position_(b)
                if (!ws(b, l + 1)) break
                if (!GeneratedParserUtilBase.empty_element_parsed_guard_(b, "item__2", c)) break
            }
            return true
        }

        // response?
        private fun item__3(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "item__3")) return false
            response(b, l + 1)
            return true
        }

        /* ********************************************************** */ // COMMENT? OPTION {OPTION|COMMENT|ws}*
        fun options(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "options")) return false
            if (!GeneratedParserUtilBase.nextTokenIs(b, "<options>", RestTypes.COMMENT, RestTypes.OPTION)) return false
            var r: Boolean
            val m = GeneratedParserUtilBase.enter_section_(
                b,
                l,
                GeneratedParserUtilBase._NONE_,
                RestTypes.OPTIONS,
                "<options>"
            )
            r = options_0(b, l + 1)
            r = r && GeneratedParserUtilBase.consumeToken(b, RestTypes.OPTION)
            r = r && options_2(b, l + 1)
            GeneratedParserUtilBase.exit_section_(b, l, m, r, false, null)
            return r
        }

        // COMMENT?
        private fun options_0(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "options_0")) return false
            GeneratedParserUtilBase.consumeToken(b, RestTypes.COMMENT)
            return true
        }

        // {OPTION|COMMENT|ws}*
        private fun options_2(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "options_2")) return false
            while (true) {
                val c = GeneratedParserUtilBase.current_position_(b)
                if (!options_2_0(b, l + 1)) break
                if (!GeneratedParserUtilBase.empty_element_parsed_guard_(b, "options_2", c)) break
            }
            return true
        }

        // OPTION|COMMENT|ws
        private fun options_2_0(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "options_2_0")) return false
            var r: Boolean
            r = GeneratedParserUtilBase.consumeToken(b, RestTypes.OPTION)
            if (!r) r = GeneratedParserUtilBase.consumeToken(b, RestTypes.COMMENT)
            if (!r) r = ws(b, l + 1)
            return r
        }

        /* ********************************************************** */ // e_param {e_param|COMMENT|ws}*
        fun params(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "params")) return false
            if (!GeneratedParserUtilBase.nextTokenIs(b, RestTypes.PARAM)) return false
            var r: Boolean
            val m = GeneratedParserUtilBase.enter_section_(b)
            r = e_param(b, l + 1)
            r = r && params_1(b, l + 1)
            GeneratedParserUtilBase.exit_section_(b, m, RestTypes.PARAMS, r)
            return r
        }

        // {e_param|COMMENT|ws}*
        private fun params_1(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "params_1")) return false
            while (true) {
                val c = GeneratedParserUtilBase.current_position_(b)
                if (!params_1_0(b, l + 1)) break
                if (!GeneratedParserUtilBase.empty_element_parsed_guard_(b, "params_1", c)) break
            }
            return true
        }

        // e_param|COMMENT|ws
        private fun params_1_0(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "params_1_0")) return false
            var r: Boolean
            r = e_param(b, l + 1)
            if (!r) r = GeneratedParserUtilBase.consumeToken(b, RestTypes.COMMENT)
            if (!r) r = ws(b, l + 1)
            return r
        }

        /* ********************************************************** */ // ws* comments? options? comments? e_method? ws* comments? e_url ws* comments? params? comments? headers? comments? request_body?
        fun request(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "request")) return false
            var r: Boolean
            val m = GeneratedParserUtilBase.enter_section_(
                b,
                l,
                GeneratedParserUtilBase._NONE_,
                RestTypes.REQUEST,
                "<request>"
            )
            r = request_0(b, l + 1)
            r = r && request_1(b, l + 1)
            r = r && request_2(b, l + 1)
            r = r && request_3(b, l + 1)
            r = r && request_4(b, l + 1)
            r = r && request_5(b, l + 1)
            r = r && request_6(b, l + 1)
            r = r && e_url(b, l + 1)
            r = r && request_8(b, l + 1)
            r = r && request_9(b, l + 1)
            r = r && request_10(b, l + 1)
            r = r && request_11(b, l + 1)
            r = r && request_12(b, l + 1)
            r = r && request_13(b, l + 1)
            r = r && request_14(b, l + 1)
            GeneratedParserUtilBase.exit_section_(b, l, m, r, false, null)
            return r
        }

        // ws*
        private fun request_0(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "request_0")) return false
            while (true) {
                val c = GeneratedParserUtilBase.current_position_(b)
                if (!ws(b, l + 1)) break
                if (!GeneratedParserUtilBase.empty_element_parsed_guard_(b, "request_0", c)) break
            }
            return true
        }

        // comments?
        private fun request_1(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "request_1")) return false
            comments(b, l + 1)
            return true
        }

        // options?
        private fun request_2(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "request_2")) return false
            options(b, l + 1)
            return true
        }

        // comments?
        private fun request_3(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "request_3")) return false
            comments(b, l + 1)
            return true
        }

        // e_method?
        private fun request_4(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "request_4")) return false
            e_method(b, l + 1)
            return true
        }

        // ws*
        private fun request_5(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "request_5")) return false
            while (true) {
                val c = GeneratedParserUtilBase.current_position_(b)
                if (!ws(b, l + 1)) break
                if (!GeneratedParserUtilBase.empty_element_parsed_guard_(b, "request_5", c)) break
            }
            return true
        }

        // comments?
        private fun request_6(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "request_6")) return false
            comments(b, l + 1)
            return true
        }

        // ws*
        private fun request_8(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "request_8")) return false
            while (true) {
                val c = GeneratedParserUtilBase.current_position_(b)
                if (!ws(b, l + 1)) break
                if (!GeneratedParserUtilBase.empty_element_parsed_guard_(b, "request_8", c)) break
            }
            return true
        }

        // comments?
        private fun request_9(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "request_9")) return false
            comments(b, l + 1)
            return true
        }

        // params?
        private fun request_10(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "request_10")) return false
            params(b, l + 1)
            return true
        }

        // comments?
        private fun request_11(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "request_11")) return false
            comments(b, l + 1)
            return true
        }

        // headers?
        private fun request_12(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "request_12")) return false
            headers(b, l + 1)
            return true
        }

        // comments?
        private fun request_13(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "request_13")) return false
            comments(b, l + 1)
            return true
        }

        // request_body?
        private fun request_14(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "request_14")) return false
            request_body(b, l + 1)
            return true
        }

        /* ********************************************************** */ // REQUEST_BODY_LINE {REQUEST_BODY_LINE|COMMENT|ws}*
        fun request_body(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "request_body")) return false
            if (!GeneratedParserUtilBase.nextTokenIs(b, RestTypes.REQUEST_BODY_LINE)) return false
            var r: Boolean
            val m = GeneratedParserUtilBase.enter_section_(b)
            r = GeneratedParserUtilBase.consumeToken(b, RestTypes.REQUEST_BODY_LINE)
            r = r && request_body_1(b, l + 1)
            GeneratedParserUtilBase.exit_section_(b, m, RestTypes.REQUEST_BODY, r)
            return r
        }

        // {REQUEST_BODY_LINE|COMMENT|ws}*
        private fun request_body_1(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "request_body_1")) return false
            while (true) {
                val c = GeneratedParserUtilBase.current_position_(b)
                if (!request_body_1_0(b, l + 1)) break
                if (!GeneratedParserUtilBase.empty_element_parsed_guard_(b, "request_body_1", c)) break
            }
            return true
        }

        // REQUEST_BODY_LINE|COMMENT|ws
        private fun request_body_1_0(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "request_body_1_0")) return false
            var r: Boolean
            r = GeneratedParserUtilBase.consumeToken(b, RestTypes.REQUEST_BODY_LINE)
            if (!r) r = GeneratedParserUtilBase.consumeToken(b, RestTypes.COMMENT)
            if (!r) r = ws(b, l + 1)
            return r
        }

        /* ********************************************************** */ // comments? headers? comments? response_body?
        fun response(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "response")) return false
            var r: Boolean
            val m = GeneratedParserUtilBase.enter_section_(
                b,
                l,
                GeneratedParserUtilBase._NONE_,
                RestTypes.RESPONSE,
                "<response>"
            )
            r = response_0(b, l + 1)
            r = r && response_1(b, l + 1)
            r = r && response_2(b, l + 1)
            r = r && response_3(b, l + 1)
            GeneratedParserUtilBase.exit_section_(b, l, m, r, false, null)
            return r
        }

        // comments?
        private fun response_0(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "response_0")) return false
            comments(b, l + 1)
            return true
        }

        // headers?
        private fun response_1(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "response_1")) return false
            headers(b, l + 1)
            return true
        }

        // comments?
        private fun response_2(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "response_2")) return false
            comments(b, l + 1)
            return true
        }

        // response_body?
        private fun response_3(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "response_3")) return false
            response_body(b, l + 1)
            return true
        }

        /* ********************************************************** */ // RESPONSE_BODY_LINE {RESPONSE_BODY_LINE|COMMENT|ws}*
        fun response_body(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "response_body")) return false
            if (!GeneratedParserUtilBase.nextTokenIs(b, RestTypes.RESPONSE_BODY_LINE)) return false
            var r: Boolean
            val m = GeneratedParserUtilBase.enter_section_(b)
            r = GeneratedParserUtilBase.consumeToken(b, RestTypes.RESPONSE_BODY_LINE)
            r = r && response_body_1(b, l + 1)
            GeneratedParserUtilBase.exit_section_(b, m, RestTypes.RESPONSE_BODY, r)
            return r
        }

        // {RESPONSE_BODY_LINE|COMMENT|ws}*
        private fun response_body_1(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "response_body_1")) return false
            while (true) {
                val c = GeneratedParserUtilBase.current_position_(b)
                if (!response_body_1_0(b, l + 1)) break
                if (!GeneratedParserUtilBase.empty_element_parsed_guard_(b, "response_body_1", c)) break
            }
            return true
        }

        // RESPONSE_BODY_LINE|COMMENT|ws
        private fun response_body_1_0(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "response_body_1_0")) return false
            var r: Boolean
            r = GeneratedParserUtilBase.consumeToken(b, RestTypes.RESPONSE_BODY_LINE)
            if (!r) r = GeneratedParserUtilBase.consumeToken(b, RestTypes.COMMENT)
            if (!r) r = ws(b, l + 1)
            return r
        }

        /* ********************************************************** */ // item_*
        fun restFile(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "restFile")) return false
            while (true) {
                val c = GeneratedParserUtilBase.current_position_(b)
                if (!item_(b, l + 1)) break
                if (!GeneratedParserUtilBase.empty_element_parsed_guard_(b, "restFile", c)) break
            }
            return true
        }

        /* ********************************************************** */ // {WHITE_SPACE|CRLF}+
        fun ws(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "ws")) return false
            if (!GeneratedParserUtilBase.nextTokenIs(b, "<ws>", RestTypes.CRLF, RestTypes.WHITE_SPACE)) return false
            val r: Boolean
            val m = GeneratedParserUtilBase.enter_section_(b, l, GeneratedParserUtilBase._NONE_, RestTypes.WS, "<ws>")
            r = ws_0(b, l + 1)
            while (r) {
                val c = GeneratedParserUtilBase.current_position_(b)
                if (!ws_0(b, l + 1)) break
                if (!GeneratedParserUtilBase.empty_element_parsed_guard_(b, "ws", c)) break
            }
            GeneratedParserUtilBase.exit_section_(b, l, m, r, false, null)
            return r
        }

        // WHITE_SPACE|CRLF
        private fun ws_0(b: PsiBuilder?, l: Int): Boolean {
            if (!GeneratedParserUtilBase.recursion_guard_(b, l, "ws_0")) return false
            var r: Boolean
            r = GeneratedParserUtilBase.consumeToken(b, RestTypes.WHITE_SPACE)
            if (!r) r = GeneratedParserUtilBase.consumeToken(b, RestTypes.CRLF)
            return r
        }
    }
}