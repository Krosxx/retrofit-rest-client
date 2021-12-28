package cn.vove7.plugin.rest.lineprovider

import cn.vove7.plugin.rest.tool.trimText
import com.intellij.psi.PsiAnnotation
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethod
//import org.jetbrains.kotlin.asJava.LightClassUtil
//import org.jetbrains.kotlin.asJava.elements.KtLightMethod
//import org.jetbrains.kotlin.psi.KtNamedFunction


/**
 * # RetrofitLineKotlinMarkerProvider
 * Created by 11324.
 * Date: 2019/9/28
 */
class RetrofitLineKotlinMarkerProvider : RetrofitLineMarkerProvider() {

    override fun getMethod(ele: PsiElement): PsiMethod? {
//        val e = ele.context
        if (ele::class.java.simpleName == "KtNamedFunction") {
            return ele.toPsiMethod()
        }
        return null
    }

    val LightClassUtil_CLS by lazy {
        Class.forName("org.jetbrains.kotlin.asJava.LightClassUtil")
    }

    val LightClassUtil_INS by lazy {
        LightClassUtil_CLS.getField("INSTANCE").get(null)
    }

    val getLightClassMethod by lazy {
        LightClassUtil_CLS.getMethod("getLightClassMethod", Class.forName("org.jetbrains.kotlin.psi.KtFunction"))
    }

//    private fun KtNamedFunction.toPsiMethod(): PsiMethod? = LightClassUtil.getLightClassMethod(this)
    private fun PsiElement.toPsiMethod(): PsiMethod? = getLightClassMethod.invoke(LightClassUtil_INS, this) as PsiMethod?


    override fun parseHeadersFromMethodAnnotation(method: PsiMethod, annotation: PsiAnnotation): List<String> {
        val list = mutableListOf<String>()
        val attrs = annotation.parameterList.attributes
//        if (method is KtLightMethod) {//kotlin fun
//            attrs.forEach { s ->
//                list += s.trimText ?: ""
//            }
//        }
        return list
    }
}