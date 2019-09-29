package cn.vove7.plugin.rest.model

class ResponseModel(
        val status: Int,
        val headers: List<String>,
        val contentType: String?,
        val body: String?
) {

    fun headersString(): String = buildString {
        appendln()
        for (header in headers) {
            append("@").append(header).append("\n")
        }
        appendln()


    }
}