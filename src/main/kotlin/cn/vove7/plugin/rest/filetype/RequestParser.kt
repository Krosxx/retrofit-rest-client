package cn.vove7.plugin.rest.filetype

import cn.vove7.plugin.rest.model.RequestModel
import cn.vove7.plugin.rest.psi.RestRequest

/**
 * @author danblack
 */
object RequestParser {
    private fun getMethod(request: RestRequest): RequestModel.Method {
        val method = request.eMethod
        if (method != null && method.isValid) {
            when (method.text) {
                "GET" -> return RequestModel.Method.GET
                "PUT" -> return RequestModel.Method.PUT
                "POST" -> return RequestModel.Method.POST
                "DELETE" -> return RequestModel.Method.DELETE
                "PATCH" -> return RequestModel.Method.PATCH
            }
            throw IllegalStateException()
        }
        return RequestModel.Method.GET
    }

    fun parse(request: RestRequest): RequestModel {
        val method = getMethod(request)
        val url = getUrl(request)
        var body = getBody(request)
        val headers = getHeaders(request)
        val params: MutableMap<String, String> = HashMap()
        if (method !== RequestModel.Method.GET && "application/x-www-form-urlencoded" == headers["Content-Type"]) {
            bodyToParam(body, params)
            body = null
        }
        return RequestModel(method, url, headers, params, body)
    }

    private fun bodyToParam(body: String?, ps: MutableMap<String, String>) {
        if (body == null) return
        val kvs = body.trim { it <= ' ' }.split("&".toRegex()).toTypedArray()
        for (kv in kvs) {
            val kvss = kv.split("=".toRegex()).toTypedArray()
            if (kvss.size == 2) {
                val k = kvss[0].trim { it <= ' ' }
                val v = kvss[1].trim { it <= ' ' }
                ps[k] = v
            }
        }
    }

    private fun getHeaders(request: RestRequest): MutableMap<String, String> {
        val result: MutableMap<String, String> = HashMap()
        val headers = request.headers
        if (headers != null) {
            for (header in headers.eHeaderList) {
                val text = header.text
                var i = text.indexOf("@")
                if (i >= 0) {
                    i = text.indexOf(":")
                    if (i >= 0) {
                        val name = text.substring(1, i).trim { it <= ' ' }
                        val value = text.substring(i + 1).trim { it <= ' ' }
                        result[name] = value
                    }
                }
            }
        }
        return result
    }

    private fun getBody(request: RestRequest): String? {
        val body = request.requestBody
        return if (body != null && body.isValid) {
            body.text
        } else null
    }

    private fun getUrl(request: RestRequest): String {
        val url = request.eUrl
        if (url.isValid) {
            val urlText = url.text
            val markExists = urlText.contains("?")
            val sb = StringBuilder(urlText)
            val params = request.params
            if (params != null && params.isValid) {
                if (!markExists) {
                    sb.append('?')
                }
                for (param in params.eParamList) {
                    sb.append(param.text)
                }
            }
            return sb.toString()
        }
        throw IllegalStateException("")
    }
}