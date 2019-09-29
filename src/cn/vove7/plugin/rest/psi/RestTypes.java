// This is a generated file. Not intended for manual editing.
package cn.vove7.plugin.rest.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import cn.vove7.plugin.rest.psi.impl.*;

public interface RestTypes {

  IElementType COMMENTS = new RestElementType("COMMENTS");
  IElementType E_BAD = new RestElementType("E_BAD");
  IElementType E_HEADER = new RestElementType("E_HEADER");
  IElementType E_METHOD = new RestElementType("E_METHOD");
  IElementType E_PARAM = new RestElementType("E_PARAM");
  IElementType E_SEPARATOR = new RestElementType("E_SEPARATOR");
  IElementType E_URL = new RestElementType("E_URL");
  IElementType HEADERS = new RestElementType("HEADERS");
  IElementType OPTIONS = new RestElementType("OPTIONS");
  IElementType PARAMS = new RestElementType("PARAMS");
  IElementType REQUEST = new RestElementType("REQUEST");
  IElementType REQUEST_BODY = new RestElementType("REQUEST_BODY");
  IElementType RESPONSE = new RestElementType("RESPONSE");
  IElementType RESPONSE_BODY = new RestElementType("RESPONSE_BODY");
  IElementType WS = new RestElementType("WS");

  IElementType BAD_CHARACTER = new RestTokenType("BAD_CHARACTER");
  IElementType COMMENT = new RestTokenType("COMMENT");
  IElementType CRLF = new RestTokenType("CRLF");
  IElementType HEADER = new RestTokenType("HEADER");
  IElementType METHOD = new RestTokenType("METHOD");
  IElementType OPTION = new RestTokenType("OPTION");
  IElementType PARAM = new RestTokenType("PARAM");
  IElementType REQUEST_BODY_LINE = new RestTokenType("REQUEST_BODY_LINE");
  IElementType RESPONSE_BODY_LINE = new RestTokenType("RESPONSE_BODY_LINE");
  IElementType SEPARATOR = new RestTokenType("SEPARATOR");
  IElementType URL = new RestTokenType("URL");
  IElementType WHITE_SPACE = new RestTokenType("WHITE_SPACE");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
      if (type == COMMENTS) {
        return new RestCommentsImpl(node);
      }
      else if (type == E_BAD) {
        return new RestEBadImpl(node);
      }
      else if (type == E_HEADER) {
        return new RestEHeaderImpl(node);
      }
      else if (type == E_METHOD) {
        return new RestEMethodImpl(node);
      }
      else if (type == E_PARAM) {
        return new RestEParamImpl(node);
      }
      else if (type == E_SEPARATOR) {
        return new RestESeparatorImpl(node);
      }
      else if (type == E_URL) {
        return new RestEUrlImpl(node);
      }
      else if (type == HEADERS) {
        return new RestHeadersImpl(node);
      }
      else if (type == OPTIONS) {
        return new RestOptionsImpl(node);
      }
      else if (type == PARAMS) {
        return new RestParamsImpl(node);
      }
      else if (type == REQUEST) {
        return new RestRequestImpl(node);
      }
      else if (type == REQUEST_BODY) {
        return new RestRequestBodyImpl(node);
      }
      else if (type == RESPONSE) {
        return new RestResponseImpl(node);
      }
      else if (type == RESPONSE_BODY) {
        return new RestResponseBodyImpl(node);
      }
      else if (type == WS) {
        return new RestWsImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
