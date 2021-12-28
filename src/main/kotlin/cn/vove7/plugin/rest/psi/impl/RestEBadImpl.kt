// This is a generated file. Not intended for manual editing.
package cn.vove7.plugin.rest.psi.impl

import cn.vove7.plugin.rest.psi.RestEBad
import cn.vove7.plugin.rest.psi.RestVisitor
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElementVisitor

class RestEBadImpl(node: ASTNode) : ASTWrapperPsiElement(node), RestEBad {
    fun accept(visitor: RestVisitor) {
        visitor.visitEBad(this)
    }

    override fun accept(visitor: PsiElementVisitor) {
        if (visitor is RestVisitor) accept(visitor) else super.accept(visitor)
    }
}