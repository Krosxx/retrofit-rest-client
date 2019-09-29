// This is a generated file. Not intended for manual editing.
package cn.vove7.plugin.rest.psi.impl;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.lang.ASTNode;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.util.PsiTreeUtil;
import static cn.vove7.plugin.rest.psi.RestTypes.*;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import cn.vove7.plugin.rest.psi.*;

public class RestEMethodImpl extends ASTWrapperPsiElement implements RestEMethod {

  public RestEMethodImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RestVisitor visitor) {
    visitor.visitEMethod(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RestVisitor) accept((RestVisitor)visitor);
    else super.accept(visitor);
  }

}
