// This is a generated file. Not intended for manual editing.
package cn.vove7.plugin.rest.psi

import com.intellij.psi.PsiElement
import cn.vove7.plugin.rest.psi.RestEHeader
import cn.vove7.plugin.rest.psi.RestWs

interface RestHeaders : PsiElement {
    val eHeaderList: List<RestEHeader>
    val wsList: List<RestWs?>
}