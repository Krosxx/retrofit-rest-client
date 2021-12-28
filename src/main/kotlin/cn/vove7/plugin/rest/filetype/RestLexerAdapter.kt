package cn.vove7.plugin.rest.filetype

import com.intellij.lexer.MergingLexerAdapter
import com.intellij.lexer.FlexAdapter
import cn.vove7.plugin.rest.filetype.RestLexer
import com.intellij.psi.tree.TokenSet
import cn.vove7.plugin.rest.psi.RestTypes

/**
 *
 */
class RestLexerAdapter :
    MergingLexerAdapter(FlexAdapter(RestLexer(null)), TokenSet.create(RestTypes.BAD_CHARACTER))