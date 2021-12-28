// This is a generated file. Not intended for manual editing.
package cn.vove7.plugin.rest.psi.impl

import cn.vove7.plugin.rest.psi.*
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.util.PsiTreeUtil

class RestResponseImpl(node: ASTNode) : ASTWrapperPsiElement(node), RestResponse {
    fun accept(visitor: RestVisitor) {
        visitor.visitResponse(this)
    }

    override fun accept(visitor: PsiElementVisitor) {
        if (visitor is RestVisitor) accept(visitor) else super.accept(visitor)
    }

    override val commentsList: List<RestComments>
        get() = PsiTreeUtil.getChildrenOfTypeAsList(this, RestComments::class.java)
    override val headers: RestHeaders?
        get() = findChildByClass(RestHeaders::class.java)
    override val responseBody: RestResponseBody?
        get() = findChildByClass(RestResponseBody::class.java)
}