package cn.vove7.plugin.rest.lineprovider

import cn.vove7.plugin.rest.tool.trimText
import com.intellij.psi.PsiAnnotation
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethod
import org.jetbrains.kotlin.asJava.LightClassUtil
import org.jetbrains.kotlin.asJava.elements.KtLightMethod
import org.jetbrains.kotlin.psi.KtNamedFunction


/**
 * # RetrofitLineKotlinMarkerProvider
 * Created by 11324.
 * Date: 2019/9/28
 */
class RetrofitLineKotlinMarkerProvider : RetrofitLineMarkerProvider() {

    override fun getMethod(ele: PsiElement): PsiMethod? {
        if (ele is KtNamedFunction) {
            return ele.toPsiMethod()
        }
        return null
    }

    private fun KtNamedFunction.toPsiMethod(): PsiMethod? = LightClassUtil.getLightClassMethod(this)

    override fun parseHeadersFromMethodAnnotation(method: PsiMethod, annotation: PsiAnnotation): List<String> {
        val list = mutableListOf<String>()
        val attrs = annotation.parameterList.attributes
        if (method is KtLightMethod) {//kotlin fun
            attrs.forEach { s ->
                list += s.trimText ?: ""
            }
        }
        return list
    }
}