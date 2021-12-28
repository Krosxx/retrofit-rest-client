// This is a generated file. Not intended for manual editing.
package cn.vove7.plugin.rest.psi.impl

import cn.vove7.plugin.rest.psi.RestVisitor
import cn.vove7.plugin.rest.psi.RestWs
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElementVisitor

class RestWsImpl(node: ASTNode) : ASTWrapperPsiElement(node), RestWs {
    fun accept(visitor: RestVisitor) {
        visitor.visitWs(this)
    }

    override fun accept(visitor: PsiElementVisitor) {
        if (visitor is RestVisitor) accept(visitor) else super.accept(visitor)
    }
}