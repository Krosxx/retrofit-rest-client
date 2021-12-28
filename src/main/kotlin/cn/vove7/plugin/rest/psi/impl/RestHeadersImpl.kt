// This is a generated file. Not intended for manual editing.
package cn.vove7.plugin.rest.psi.impl

import cn.vove7.plugin.rest.psi.RestEHeader
import cn.vove7.plugin.rest.psi.RestHeaders
import cn.vove7.plugin.rest.psi.RestVisitor
import cn.vove7.plugin.rest.psi.RestWs
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.util.PsiTreeUtil

class RestHeadersImpl(node: ASTNode) : ASTWrapperPsiElement(node), RestHeaders {
    fun accept(visitor: RestVisitor) {
        visitor.visitHeaders(this)
    }

    override fun accept(visitor: PsiElementVisitor) {
        if (visitor is RestVisitor) accept(visitor) else super.accept(visitor)
    }

    override val eHeaderList: List<RestEHeader>
        get() = PsiTreeUtil.getChildrenOfTypeAsList(this, RestEHeader::class.java)
    override val wsList: List<RestWs>
        get() = PsiTreeUtil.getChildrenOfTypeAsList(this, RestWs::class.java)
}