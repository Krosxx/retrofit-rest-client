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

public class RestRequestImpl extends ASTWrapperPsiElement implements RestRequest {

  public RestRequestImpl(@NotNull ASTNode node) {
    super(node);
  }

  public void accept(@NotNull RestVisitor visitor) {
    visitor.visitRequest(this);
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
  public RestEMethod getEMethod() {
    return findChildByClass(RestEMethod.class);
  }

  @Override
  @NotNull
  public RestEUrl getEUrl() {
    return findNotNullChildByClass(RestEUrl.class);
  }

  @Override
  @Nullable
  public RestHeaders getHeaders() {
    return findChildByClass(RestHeaders.class);
  }

  @Override
  @Nullable
  public RestOptions getOptions() {
    return findChildByClass(RestOptions.class);
  }

  @Override
  @Nullable
  public RestParams getParams() {
    return findChildByClass(RestParams.class);
  }

  @Override
  @Nullable
  public RestRequestBody getRequestBody() {
    return findChildByClass(RestRequestBody.class);
  }

  @Override
  @NotNull
  public List<RestWs> getWsList() {
    return PsiTreeUtil.getChildrenOfTypeAsList(this, RestWs.class);
  }

}
