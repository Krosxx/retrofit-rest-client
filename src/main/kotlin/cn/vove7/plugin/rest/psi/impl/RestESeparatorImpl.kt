// This is a generated file. Not intended for manual editing.
package cn.vove7.plugin.rest.psi.impl

import cn.vove7.plugin.rest.psi.RestESeparator
import cn.vove7.plugin.rest.psi.RestVisitor
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElementVisitor

class RestESeparatorImpl(node: ASTNode) : ASTWrapperPsiElement(node), RestESeparator {
    fun accept(visitor: RestVisitor) {
        visitor.visitESeparator(this)
    }

    override fun accept(visitor: PsiElementVisitor) {
        if (visitor is RestVisitor) accept(visitor) else super.accept(visitor)
    }
}