// This is a generated file. Not intended for manual editing.
package cn.vove7.plugin.rest.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static cn.vove7.plugin.rest.psi.RestTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class RestParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    r = parse_root_(t, b);
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b) {
    return parse_root_(t, b, 0);
  }

  static boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return restFile(b, l + 1);
  }

  /* ********************************************************** */
  // COMMENT {COMMENT|ws}*
  public static boolean comments(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comments")) return false;
    if (!nextTokenIs(b, COMMENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMENT);
    r = r && comments_1(b, l + 1);
    exit_section_(b, m, COMMENTS, r);
    return r;
  }

  // {COMMENT|ws}*
  private static boolean comments_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comments_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!comments_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "comments_1", c)) break;
    }
    return true;
  }

  // COMMENT|ws
  private static boolean comments_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comments_1_0")) return false;
    boolean r;
    r = consumeToken(b, COMMENT);
    if (!r) r = ws(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // BAD_CHARACTER
  public static boolean e_bad(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "e_bad")) return false;
    if (!nextTokenIs(b, BAD_CHARACTER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BAD_CHARACTER);
    exit_section_(b, m, E_BAD, r);
    return r;
  }

  /* ********************************************************** */
  // HEADER
  public static boolean e_header(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "e_header")) return false;
    if (!nextTokenIs(b, HEADER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, HEADER);
    exit_section_(b, m, E_HEADER, r);
    return r;
  }

  /* ********************************************************** */
  // METHOD
  public static boolean e_method(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "e_method")) return false;
    if (!nextTokenIs(b, METHOD)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, METHOD);
    exit_section_(b, m, E_METHOD, r);
    return r;
  }

  /* ********************************************************** */
  // PARAM
  public static boolean e_param(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "e_param")) return false;
    if (!nextTokenIs(b, PARAM)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, PARAM);
    exit_section_(b, m, E_PARAM, r);
    return r;
  }

  /* ********************************************************** */
  // SEPARATOR
  public static boolean e_separator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "e_separator")) return false;
    if (!nextTokenIs(b, SEPARATOR)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, SEPARATOR);
    exit_section_(b, m, E_SEPARATOR, r);
    return r;
  }

  /* ********************************************************** */
  // URL
  public static boolean e_url(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "e_url")) return false;
    if (!nextTokenIs(b, URL)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, URL);
    exit_section_(b, m, E_URL, r);
    return r;
  }

  /* ********************************************************** */
  // e_header {e_header|COMMENT|ws}*
  public static boolean headers(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "headers")) return false;
    if (!nextTokenIs(b, HEADER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = e_header(b, l + 1);
    r = r && headers_1(b, l + 1);
    exit_section_(b, m, HEADERS, r);
    return r;
  }

  // {e_header|COMMENT|ws}*
  private static boolean headers_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "headers_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!headers_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "headers_1", c)) break;
    }
    return true;
  }

  // e_header|COMMENT|ws
  private static boolean headers_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "headers_1_0")) return false;
    boolean r;
    r = e_header(b, l + 1);
    if (!r) r = consumeToken(b, COMMENT);
    if (!r) r = ws(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // request e_separator? ws* response?
  static boolean item_(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item_")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = request(b, l + 1);
    r = r && item__1(b, l + 1);
    r = r && item__2(b, l + 1);
    r = r && item__3(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // e_separator?
  private static boolean item__1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item__1")) return false;
    e_separator(b, l + 1);
    return true;
  }

  // ws*
  private static boolean item__2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item__2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ws(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "item__2", c)) break;
    }
    return true;
  }

  // response?
  private static boolean item__3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "item__3")) return false;
    response(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // COMMENT? OPTION {OPTION|COMMENT|ws}*
  public static boolean options(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "options")) return false;
    if (!nextTokenIs(b, "<options>", COMMENT, OPTION)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, OPTIONS, "<options>");
    r = options_0(b, l + 1);
    r = r && consumeToken(b, OPTION);
    r = r && options_2(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // COMMENT?
  private static boolean options_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "options_0")) return false;
    consumeToken(b, COMMENT);
    return true;
  }

  // {OPTION|COMMENT|ws}*
  private static boolean options_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "options_2")) return false;
    while (true) {
      int c = current_position_(b);
      if (!options_2_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "options_2", c)) break;
    }
    return true;
  }

  // OPTION|COMMENT|ws
  private static boolean options_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "options_2_0")) return false;
    boolean r;
    r = consumeToken(b, OPTION);
    if (!r) r = consumeToken(b, COMMENT);
    if (!r) r = ws(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // e_param {e_param|COMMENT|ws}*
  public static boolean params(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "params")) return false;
    if (!nextTokenIs(b, PARAM)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = e_param(b, l + 1);
    r = r && params_1(b, l + 1);
    exit_section_(b, m, PARAMS, r);
    return r;
  }

  // {e_param|COMMENT|ws}*
  private static boolean params_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "params_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!params_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "params_1", c)) break;
    }
    return true;
  }

  // e_param|COMMENT|ws
  private static boolean params_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "params_1_0")) return false;
    boolean r;
    r = e_param(b, l + 1);
    if (!r) r = consumeToken(b, COMMENT);
    if (!r) r = ws(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // ws* comments? options? comments? e_method? ws* comments? e_url ws* comments? params? comments? headers? comments? request_body?
  public static boolean request(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "request")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, REQUEST, "<request>");
    r = request_0(b, l + 1);
    r = r && request_1(b, l + 1);
    r = r && request_2(b, l + 1);
    r = r && request_3(b, l + 1);
    r = r && request_4(b, l + 1);
    r = r && request_5(b, l + 1);
    r = r && request_6(b, l + 1);
    r = r && e_url(b, l + 1);
    r = r && request_8(b, l + 1);
    r = r && request_9(b, l + 1);
    r = r && request_10(b, l + 1);
    r = r && request_11(b, l + 1);
    r = r && request_12(b, l + 1);
    r = r && request_13(b, l + 1);
    r = r && request_14(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ws*
  private static boolean request_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "request_0")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ws(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "request_0", c)) break;
    }
    return true;
  }

  // comments?
  private static boolean request_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "request_1")) return false;
    comments(b, l + 1);
    return true;
  }

  // options?
  private static boolean request_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "request_2")) return false;
    options(b, l + 1);
    return true;
  }

  // comments?
  private static boolean request_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "request_3")) return false;
    comments(b, l + 1);
    return true;
  }

  // e_method?
  private static boolean request_4(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "request_4")) return false;
    e_method(b, l + 1);
    return true;
  }

  // ws*
  private static boolean request_5(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "request_5")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ws(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "request_5", c)) break;
    }
    return true;
  }

  // comments?
  private static boolean request_6(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "request_6")) return false;
    comments(b, l + 1);
    return true;
  }

  // ws*
  private static boolean request_8(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "request_8")) return false;
    while (true) {
      int c = current_position_(b);
      if (!ws(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "request_8", c)) break;
    }
    return true;
  }

  // comments?
  private static boolean request_9(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "request_9")) return false;
    comments(b, l + 1);
    return true;
  }

  // params?
  private static boolean request_10(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "request_10")) return false;
    params(b, l + 1);
    return true;
  }

  // comments?
  private static boolean request_11(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "request_11")) return false;
    comments(b, l + 1);
    return true;
  }

  // headers?
  private static boolean request_12(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "request_12")) return false;
    headers(b, l + 1);
    return true;
  }

  // comments?
  private static boolean request_13(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "request_13")) return false;
    comments(b, l + 1);
    return true;
  }

  // request_body?
  private static boolean request_14(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "request_14")) return false;
    request_body(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // REQUEST_BODY_LINE {REQUEST_BODY_LINE|COMMENT|ws}*
  public static boolean request_body(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "request_body")) return false;
    if (!nextTokenIs(b, REQUEST_BODY_LINE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, REQUEST_BODY_LINE);
    r = r && request_body_1(b, l + 1);
    exit_section_(b, m, REQUEST_BODY, r);
    return r;
  }

  // {REQUEST_BODY_LINE|COMMENT|ws}*
  private static boolean request_body_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "request_body_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!request_body_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "request_body_1", c)) break;
    }
    return true;
  }

  // REQUEST_BODY_LINE|COMMENT|ws
  private static boolean request_body_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "request_body_1_0")) return false;
    boolean r;
    r = consumeToken(b, REQUEST_BODY_LINE);
    if (!r) r = consumeToken(b, COMMENT);
    if (!r) r = ws(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // comments? headers? comments? response_body?
  public static boolean response(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "response")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, RESPONSE, "<response>");
    r = response_0(b, l + 1);
    r = r && response_1(b, l + 1);
    r = r && response_2(b, l + 1);
    r = r && response_3(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // comments?
  private static boolean response_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "response_0")) return false;
    comments(b, l + 1);
    return true;
  }

  // headers?
  private static boolean response_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "response_1")) return false;
    headers(b, l + 1);
    return true;
  }

  // comments?
  private static boolean response_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "response_2")) return false;
    comments(b, l + 1);
    return true;
  }

  // response_body?
  private static boolean response_3(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "response_3")) return false;
    response_body(b, l + 1);
    return true;
  }

  /* ********************************************************** */
  // RESPONSE_BODY_LINE {RESPONSE_BODY_LINE|COMMENT|ws}*
  public static boolean response_body(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "response_body")) return false;
    if (!nextTokenIs(b, RESPONSE_BODY_LINE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, RESPONSE_BODY_LINE);
    r = r && response_body_1(b, l + 1);
    exit_section_(b, m, RESPONSE_BODY, r);
    return r;
  }

  // {RESPONSE_BODY_LINE|COMMENT|ws}*
  private static boolean response_body_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "response_body_1")) return false;
    while (true) {
      int c = current_position_(b);
      if (!response_body_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "response_body_1", c)) break;
    }
    return true;
  }

  // RESPONSE_BODY_LINE|COMMENT|ws
  private static boolean response_body_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "response_body_1_0")) return false;
    boolean r;
    r = consumeToken(b, RESPONSE_BODY_LINE);
    if (!r) r = consumeToken(b, COMMENT);
    if (!r) r = ws(b, l + 1);
    return r;
  }

  /* ********************************************************** */
  // item_*
  static boolean restFile(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "restFile")) return false;
    while (true) {
      int c = current_position_(b);
      if (!item_(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "restFile", c)) break;
    }
    return true;
  }

  /* ********************************************************** */
  // {WHITE_SPACE|CRLF}+
  public static boolean ws(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ws")) return false;
    if (!nextTokenIs(b, "<ws>", CRLF, WHITE_SPACE)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, WS, "<ws>");
    r = ws_0(b, l + 1);
    while (r) {
      int c = current_position_(b);
      if (!ws_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "ws", c)) break;
    }
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // WHITE_SPACE|CRLF
  private static boolean ws_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "ws_0")) return false;
    boolean r;
    r = consumeToken(b, WHITE_SPACE);
    if (!r) r = consumeToken(b, CRLF);
    return r;
  }

}
