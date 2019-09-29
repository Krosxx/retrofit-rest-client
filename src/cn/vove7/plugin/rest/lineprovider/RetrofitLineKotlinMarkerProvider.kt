package cn.vove7.plugin.rest.lineprovider

import com.intellij.psi.PsiElement
import com.intellij.psi.PsiMethod
import org.jetbrains.kotlin.asJava.LightClassUtil
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

}