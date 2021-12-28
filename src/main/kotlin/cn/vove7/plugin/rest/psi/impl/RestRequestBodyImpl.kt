// This is a generated file. Not intended for manual editing.
package cn.vove7.plugin.rest.psi.impl

import cn.vove7.plugin.rest.psi.RestRequestBody
import cn.vove7.plugin.rest.psi.RestVisitor
import cn.vove7.plugin.rest.psi.RestWs
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.util.PsiTreeUtil

class RestRequestBodyImpl(node: ASTNode?) : RestElementImpl(node!!), RestRequestBody {
    fun accept(visitor: RestVisitor) {
        visitor.visitRequestBody(this)
    }

    override fun accept(visitor: PsiElementVisitor) {
        if (visitor is RestVisitor) accept(visitor) else super.accept(visitor)
    }

    override val wsList: List<RestWs>
        get() = PsiTreeUtil.getChildrenOfTypeAsList(this, RestWs::class.java)
}