package cn.vove7.plugin.rest.filetype

import com.intellij.lang.ParserDefinition
import cn.vove7.plugin.rest.filetype.RestLexerAdapter
import com.intellij.lang.PsiParser
import cn.vove7.plugin.rest.parser.RestParser
import com.intellij.psi.tree.IFileElementType
import cn.vove7.plugin.rest.filetype.RestParserDefinition
import com.intellij.psi.tree.TokenSet
import com.intellij.psi.PsiElement
import cn.vove7.plugin.rest.psi.RestTypes
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiFile
import cn.vove7.plugin.rest.filetype.RestFile
import com.intellij.lang.ParserDefinition.SpaceRequirements
import cn.vove7.plugin.rest.filetype.RestLanguage
import com.intellij.lang.ASTNode
import com.intellij.lang.Language
import com.intellij.lexer.Lexer
import com.intellij.openapi.project.Project
import com.intellij.psi.TokenType

/**
 *
 */
class RestParserDefinition : ParserDefinition {
    override fun createLexer(project: Project): Lexer {
        return RestLexerAdapter()
    }

    override fun createParser(project: Project): PsiParser {
        return RestParser()
    }

    override fun getFileNodeType(): IFileElementType {
        return FILE
    }

    override fun getWhitespaceTokens(): TokenSet {
        return WHITE_SPACES
    }

    override fun getCommentTokens(): TokenSet {
        return TokenSet.EMPTY
    }

    override fun getStringLiteralElements(): TokenSet {
        return TokenSet.EMPTY
    }

    override fun createElement(node: ASTNode): PsiElement {
        return RestTypes.Factory.createElement(node)
    }

    override fun createFile(viewProvider: FileViewProvider): PsiFile {
        return RestFile(viewProvider)
    }

    override fun spaceExistanceTypeBetweenTokens(left: ASTNode, right: ASTNode): SpaceRequirements? {
        return null
    }

    companion object {
        val WHITE_SPACES = TokenSet.create(TokenType.WHITE_SPACE)
        val FILE = IFileElementType(
            Language.findInstance(
                RestLanguage::class.java
            )
        )
    }
}