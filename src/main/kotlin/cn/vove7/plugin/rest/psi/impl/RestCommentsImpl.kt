// This is a generated file. Not intended for manual editing.
package cn.vove7.plugin.rest.psi.impl

import cn.vove7.plugin.rest.psi.RestComments
import cn.vove7.plugin.rest.psi.RestVisitor
import cn.vove7.plugin.rest.psi.RestWs
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.util.PsiTreeUtil

class RestCommentsImpl(node: ASTNode) : ASTWrapperPsiElement(node), RestComments {
    fun accept(visitor: RestVisitor) {
        visitor.visitComments(this)
    }

    override fun accept(visitor: PsiElementVisitor) {
        if (visitor is RestVisitor) accept(visitor) else super.accept(visitor)
    }

    override val wsList: List<RestWs>
        get() = PsiTreeUtil.getChildrenOfTypeAsList(this, RestWs::class.java)
}