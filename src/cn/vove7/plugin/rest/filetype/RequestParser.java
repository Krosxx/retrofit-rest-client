package cn.vove7.plugin.rest.filetype;


import org.jetbrains.annotations.NotNull;

import java.util.HashMap;
import java.util.Map;

import cn.vove7.plugin.rest.model.RequestModel;
import cn.vove7.plugin.rest.psi.RestEHeader;
import cn.vove7.plugin.rest.psi.RestEMethod;
import cn.vove7.plugin.rest.psi.RestEParam;
import cn.vove7.plugin.rest.psi.RestEUrl;
import cn.vove7.plugin.rest.psi.RestHeaders;
import cn.vove7.plugin.rest.psi.RestParams;
import cn.vove7.plugin.rest.psi.RestRequestBody;

/**
 * @author danblack
 */
public class RequestParser {

    private static RequestModel.Method getMethod(cn.vove7.plugin.rest.psi.RestRequest request) {
        RestEMethod method = request.getEMethod();
        if (method != null && method.isValid()) {
            switch (method.getText()) {
                case "GET":
                    return RequestModel.Method.GET;
                case "PUT":
                    return RequestModel.Method.PUT;
                case "POST":
                    return RequestModel.Method.POST;
                case "DELETE":
                    return RequestModel.Method.DELETE;
                case "PATCH":
                    return RequestModel.Method.PATCH;
            }
            throw new IllegalStateException();
        }
        return RequestModel.Method.GET;
    }

    public static RequestModel parse(cn.vove7.plugin.rest.psi.RestRequest request) {
        RequestModel.Method method = getMethod(request);
        String url = getUrl(request);
        String body = getBody(request);
        Map<String, String> headers = getHeaders(request);
        Map<String, String> params = new HashMap<>();
        if (method!= RequestModel.Method.GET && "application/x-www-form-urlencoded".equals(headers.get("Content-Type"))) {
            bodyToParam(body, params);
            body = null;
        }
        return new RequestModel(method, url, headers, params, body);
    }


    private static void bodyToParam(String body,Map<String, String> ps) {
        if (body == null) return;

        String[] kvs = body.trim().split("&");
        for (String kv : kvs) {
            String[] kvss = kv.split("=");
            if (kvss.length == 2) {
                String k = kvss[0].trim();
                String v = kvss[1].trim();
                ps.put(k, v);
            }
        }
    }

    private static Map<String, String> getHeaders(cn.vove7.plugin.rest.psi.RestRequest request) {
        Map<String, String> result = new HashMap<>();
        RestHeaders headers = request.getHeaders();
        if (headers != null) {
            for (RestEHeader header : headers.getEHeaderList()) {
                String text = header.getText();
                int i = text.indexOf("@");
                if (i >= 0) {
                    i = text.indexOf(":");
                    if (i >= 0) {
                        String name = text.substring(1, i).trim();
                        String value = text.substring(i + 1).trim();
                        result.put(name, value);
                    }
                }
            }
        }
        return result;
    }

    private static String getBody(cn.vove7.plugin.rest.psi.RestRequest request) {
        RestRequestBody body = request.getRequestBody();
        if (body != null && body.isValid()) {
            return body.getText();
        }
        return null;
    }

    @NotNull
    private static String getUrl(cn.vove7.plugin.rest.psi.RestRequest request) {
        RestEUrl url = request.getEUrl();
        if (url.isValid()) {
            String urlText = url.getText();
            boolean markExists = urlText.contains("?");
            StringBuilder sb = new StringBuilder(urlText);
            RestParams params = request.getParams();
            if (params != null && params.isValid()) {
                if (!markExists) {
                    sb.append('?');
                }
                for (RestEParam param : params.getEParamList()) {
                    sb.append(param.getText());
                }
            }
            return sb.toString();
        }
        throw new IllegalStateException("");
    }

}
