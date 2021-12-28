// This is a generated file. Not intended for manual editing.
package cn.vove7.plugin.rest.psi

import com.intellij.psi.PsiElement
import cn.vove7.plugin.rest.psi.RestEParam
import cn.vove7.plugin.rest.psi.RestWs

interface RestParams : PsiElement {
    val eParamList: List<RestEParam>
    val wsList: List<RestWs?>
}