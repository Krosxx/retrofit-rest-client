package cn.vove7.plugin.rest.filetype;

import com.intellij.lexer.Lexer;
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors;
import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase;
import com.intellij.psi.TokenType;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NotNull;
import cn.vove7.plugin.rest.psi.RestTypes;

import static com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey;

/**
 *
 */
public class RestHighlighter extends SyntaxHighlighterBase {


    public static final TextAttributesKey SEPARATOR = createTextAttributesKey("REST_SEPARATOR");
    public static final TextAttributesKey METHOD = createTextAttributesKey("REST_METHOD");
    public static final TextAttributesKey URL = createTextAttributesKey("REST_URL");
    public static final TextAttributesKey HEADER = createTextAttributesKey("REST_HEADER");
    public static final TextAttributesKey OPTION = createTextAttributesKey("REST_OPTION");
    public static final TextAttributesKey PARAM = createTextAttributesKey("REST_PARAM");
    public static final TextAttributesKey COMMENT = createTextAttributesKey("REST_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT);
    public static final TextAttributesKey BODY = createTextAttributesKey("REST_BODY");
    public static final TextAttributesKey BAD_CHARACTER = createTextAttributesKey("REST_BAD_CHARACTER");

    private static final TextAttributesKey[] SEPARATOR_KEYS = new TextAttributesKey[]{SEPARATOR};
    private static final TextAttributesKey[] METHOD_KEYS = new TextAttributesKey[]{METHOD};
    private static final TextAttributesKey[] URL_KEYS = new TextAttributesKey[]{URL};
    private static final TextAttributesKey[] HEADER_KEYS = new TextAttributesKey[]{HEADER};
    private static final TextAttributesKey[] OPTION_KEYS = new TextAttributesKey[]{OPTION};
    private static final TextAttributesKey[] PARAM_KEYS = new TextAttributesKey[]{PARAM};
    private static final TextAttributesKey[] BAD_CHAR_KEYS = new TextAttributesKey[]{BAD_CHARACTER};
    private static final TextAttributesKey[] COMMENT_KEYS = new TextAttributesKey[]{COMMENT};
    private static final TextAttributesKey[] BODY_KEYS = new TextAttributesKey[]{BODY};

    @NotNull
    @Override
    public Lexer getHighlightingLexer() {
        return new RestLexerAdapter();
    }

    @NotNull
    @Override
    public TextAttributesKey[] getTokenHighlights(IElementType tokenType) {
        if (tokenType.equals(RestTypes.SEPARATOR)) {
            return SEPARATOR_KEYS;
        } else if (tokenType.equals(RestTypes.METHOD)) {
            return METHOD_KEYS;
        } else if (tokenType.equals(RestTypes.URL)) {
            return URL_KEYS;
        } else if (tokenType.equals(RestTypes.HEADER)) {
            return HEADER_KEYS;
        } else if (tokenType.equals(RestTypes.OPTION)) {
            return OPTION_KEYS;
        } else if (tokenType.equals(RestTypes.PARAM)) {
            return PARAM_KEYS;
        } else if (tokenType.equals(RestTypes.COMMENT)) {
            return COMMENT_KEYS;
        } else if (tokenType.equals(RestTypes.REQUEST_BODY_LINE) || tokenType.equals(RestTypes.RESPONSE_BODY_LINE)) {
            return BODY_KEYS;
        } else if (tokenType.equals(TokenType.BAD_CHARACTER)) {
            return BAD_CHAR_KEYS;
        }
        return EMPTY;
    }
}
