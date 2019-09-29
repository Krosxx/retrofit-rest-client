package cn.vove7.plugin.rest.filetype;

import com.intellij.lexer.FlexAdapter;
import com.intellij.lexer.MergingLexerAdapter;
import com.intellij.psi.tree.TokenSet;
import cn.vove7.plugin.rest.psi.RestTypes;

/**
 *
 */
public class RestLexerAdapter extends MergingLexerAdapter {
    public RestLexerAdapter() {
        super(new FlexAdapter(new RestLexer(null)), TokenSet.create(RestTypes.BAD_CHARACTER));
    }
}
