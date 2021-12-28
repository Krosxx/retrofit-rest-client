// This is a generated file. Not intended for manual editing.
package cn.vove7.plugin.rest.psi.impl

import cn.vove7.plugin.rest.psi.*
import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.util.PsiTreeUtil

class RestRequestImpl(node: ASTNode) : ASTWrapperPsiElement(node), RestRequest {
    fun accept(visitor: RestVisitor) {
        visitor.visitRequest(this)
    }

    override fun accept(visitor: PsiElementVisitor) {
        if (visitor is RestVisitor) accept(visitor) else super.accept(visitor)
    }

    override val commentsList: List<RestComments>
        get() = PsiTreeUtil.getChildrenOfTypeAsList(this, RestComments::class.java)
    override val eMethod: RestEMethod?
        get() = findChildByClass(RestEMethod::class.java)
    override val eUrl: RestEUrl
        get() = findNotNullChildByClass(RestEUrl::class.java)
    override val headers: RestHeaders?
        get() = findChildByClass(RestHeaders::class.java)
    override val options: RestOptions?
        get() = findChildByClass(RestOptions::class.java)
    override val params: RestParams?
        get() = findChildByClass(RestParams::class.java)
    override val requestBody: RestRequestBody?
        get() = findChildByClass(RestRequestBody::class.java)
    override val wsList: List<RestWs>
        get() = PsiTreeUtil.getChildrenOfTypeAsList(this, RestWs::class.java)
}