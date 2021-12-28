package cn.vove7.plugin.rest.tool

import cn.vove7.plugin.rest.model.RequestModel
import okhttp3.FormBody
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.Request
import okhttp3.RequestBody
import okhttp3.RequestBody.Companion.toRequestBody

object Requests {

    fun createHttpRequest(request: RequestModel): Request {
        val reqBuilder = Request.Builder()
                .url(request.url)
        fillHeaders(request, reqBuilder)

        if (request.method == RequestModel.Method.GET) {
            reqBuilder.get()
            return reqBuilder.build()
        }

        val ct = request.headers.getOrDefault("Content-Type", "text/plain")
        val body: RequestBody
        body = if (ct.contains("application/x-www-form-urlencoded")) {
            FormBody.Builder().apply {
                request.params.forEach { (name, value) -> add(name, value) }
            }.build()
        } else {
            val type = ct.toMediaType()
            (request.body ?: "").toRequestBody(type)
        }
        return when (request.method) {
            RequestModel.Method.POST -> reqBuilder.post(body).build()
            RequestModel.Method.PUT -> reqBuilder.put(body).build()
            RequestModel.Method.PATCH -> reqBuilder.patch(body).build()
            RequestModel.Method.DELETE -> reqBuilder.delete(body).build()
            else -> reqBuilder.build()
        }
    }

    private fun fillHeaders(request: RequestModel, httpRequest: Request.Builder) {
        request.headers.forEach { (name, value) -> httpRequest.addHeader(name, value) }
    }
}
