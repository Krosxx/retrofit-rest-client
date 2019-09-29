package cn.vove7.plugin.rest.lineprovider

import com.intellij.psi.PsiElement
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

}
