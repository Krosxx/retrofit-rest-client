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

public class RestResponseImpl extends ASTWrapperPsiElement implements RestResponse {

  public RestResponseImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RestVisitor visitor) {
    visitor.visitResponse(this);
  }

  public void accept(@NotNull PsiElementVisitor visitor) {
    if (visitor instanceof RestVisitor) accept((RestVisitor)visitor);
    else super.accept(visitor);
  }

  @Override
  @NotNull
  public List<RestComments> getCommentsList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RestComments.class);
  }

  @Override
  @Nullable
  public RestHeaders getHeaders() {
    return findChildByClass(RestHeaders.class);
  }

  @Override
  @Nullable
  public RestResponseBody getResponseBody() {
    return findChildByClass(RestResponseBody.class);
  }

}
