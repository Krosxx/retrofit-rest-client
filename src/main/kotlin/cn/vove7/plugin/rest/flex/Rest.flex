package cn.vove7.plugin.rest;

import com.intellij.psi.tree.IElementType;
import cn.vove7.plugin.rest.psi.RestTypes;

%%

%class RestLexer
%implements FlexLexer
%unicode
%function advance
%type IElementType
%eof{  return;
%eof}

HTTP_METHOD = ("GET" | "POST" | "PUT" | "PATCH" | "DELETE")
OPTION = "--" {LINE}?
HEADER = "@" {LINE}?
PARAM = "&" {LINE}?
COMMENT = "#" {LINE}?
//URL = "http" "s"? "://" {LINE}?
URL = {LINE}
CRLF = \n|\r|\r\n
WHITE_SPACE = [\ \t\f]
SEPARATOR = {WHITE_SPACE}* "%%%" {WHITE_SPACE}*
LINE = [^\r\n]+
FULL_LINE = [^\ \t\f\r\n]+ [^\r\n]*



%state S_METHOD S_OPTION S_URL S_PARAM S_REQ_HEADER S_RESP_HEADER
%state S_RESP_BODY S_REQ_BODY

%%

<YYINITIAL, S_METHOD, S_OPTION, S_URL, S_PARAM, S_REQ_HEADER, S_RESP_HEADER> {
    {COMMENT}                       { return RestTypes.COMMENT; }
    <S_RESP_BODY, S_REQ_BODY> {
        {CRLF}                      { return RestTypes.CRLF; }
        {WHITE_SPACE}+              { return RestTypes.WHITE_SPACE; }
    }
}

<YYINITIAL> {
    {OPTION}                    { yybegin(S_OPTION); return RestTypes.OPTION; }
    {HTTP_METHOD}               { yybegin(S_METHOD); return RestTypes.METHOD; }
    {URL}                       { yybegin(S_URL); return RestTypes.URL;}
}

<S_OPTION> {
    {OPTION}                    { return RestTypes.OPTION;}
    {HTTP_METHOD}               { yybegin(S_METHOD); return RestTypes.METHOD; }
    {URL}                       { yybegin(S_URL); return RestTypes.URL;}
}

<S_METHOD> {
    {URL}                       { yybegin(S_URL); return RestTypes.URL;}
}

<S_URL> {
   {PARAM}                      { yybegin(S_PARAM); return RestTypes.PARAM;}
   {HEADER}                     { yybegin(S_REQ_HEADER); return RestTypes.HEADER;}
   {SEPARATOR}                  { yybegin(S_RESP_HEADER); return RestTypes.SEPARATOR;}
   {FULL_LINE}                       { yybegin(S_REQ_BODY); return RestTypes.REQUEST_BODY_LINE;}
}

<S_PARAM> {
    {PARAM}                     { return RestTypes.PARAM;}
    {HEADER}                    { yybegin(S_REQ_HEADER); return RestTypes.HEADER;}
    {SEPARATOR}                 { yybegin(S_RESP_HEADER); return RestTypes.SEPARATOR;}
    {FULL_LINE}                 { yybegin(S_REQ_BODY); return RestTypes.REQUEST_BODY_LINE;}
}

<S_REQ_HEADER> {
    {HEADER}                    { return RestTypes.HEADER;}
    {SEPARATOR}                 { yybegin(S_RESP_HEADER); return RestTypes.SEPARATOR;}
    {FULL_LINE}                 { yybegin(S_REQ_BODY); return RestTypes.REQUEST_BODY_LINE;}
}

<S_REQ_BODY> {
    {SEPARATOR}                 { yybegin(S_RESP_HEADER); return RestTypes.SEPARATOR;}
    {FULL_LINE}                      { yybegin(S_REQ_BODY); return RestTypes.REQUEST_BODY_LINE;}
}

<S_RESP_HEADER> {
     {HEADER}                   { return RestTypes.HEADER;}
     {FULL_LINE}                     { yybegin(S_RESP_BODY); return RestTypes.RESPONSE_BODY_LINE;}
}

<S_RESP_BODY> {
    {FULL_LINE}                      { return RestTypes.RESPONSE_BODY_LINE;}
}

. { return RestTypes.BAD_CHARACTER; }


