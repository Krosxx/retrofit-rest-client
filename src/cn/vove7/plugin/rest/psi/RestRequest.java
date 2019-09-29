// This is a generated file. Not intended for manual editing.
package cn.vove7.plugin.rest.psi;

import java.util.List;
import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElement;

public interface RestRequest extends PsiElement {

  @NotNull
  List<RestComments> getCommentsList();

  @Nullable
  RestEMethod getEMethod();

  @NotNull
  RestEUrl getEUrl();

  @Nullable
  RestHeaders getHeaders();

  @Nullable
  RestOptions getOptions();

  @Nullable
  RestParams getParams();

  @Nullable
  RestRequestBody getRequestBody();

  @NotNull
  List<RestWs> getWsList();

}