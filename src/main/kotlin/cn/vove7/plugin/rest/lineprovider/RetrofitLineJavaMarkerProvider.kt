package cn.vove7.plugin.rest.lineprovider

import cn.vove7.plugin.rest.tool.trimText
import com.intellij.psi.PsiAnnotation
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiLiteralExpression
import com.intellij.psi.PsiMethod


/**
 * # RetrofitLineJavaMarkerProvider
 *
 * Created by 11324.
 * Date: 2019/9/26
 */
class RetrofitLineJavaMarkerProvider : RetrofitLineMarkerProvider() {

    override fun getMethod(ele: PsiElement): PsiMethod? {
        return if (ele is PsiMethod) ele else null
    }

    override fun parseHeadersFromMethodAnnotation(method: PsiMethod, annotation: PsiAnnotation): List<String> {
        val list = mutableListOf<String>()
        val attrs = annotation.parameterList.attributes
        if (attrs.isNotEmpty()) {
            attrs[0].value!!.children.forEach { c ->
                if (c is PsiLiteralExpression) {
                    list += c.trimText ?: ""
                }
            }
        }
        return list
    }
}
