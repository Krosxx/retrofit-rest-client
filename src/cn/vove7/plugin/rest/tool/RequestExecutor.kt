package cn.vove7.plugin.rest.tool

import cn.vove7.plugin.rest.model.RequestModel
import com.intellij.openapi.util.Key
import com.intellij.openapi.util.UserDataHolder
import okhttp3.Call
import okhttp3.OkHttpClient
import okhttp3.Response
import cn.vove7.plugin.rest.model.ResponseModel

import java.io.IOException
import java.nio.charset.StandardCharsets
import java.util.ArrayList

class RequestExecutor {
    var state = State.WAITING

    private val httpClient = createHttpClient()

    private var httpCall: Call? = null

    val isRunning: Boolean
        get() = state == State.RUNNING

    val isWaiting: Boolean
        get() = state == State.WAITING

    enum class State {
        WAITING,
        RUNNING,
        STOPPING
    }

    fun stop() {
        httpCall?.cancel()
    }

    private fun createHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().build()
    }

    @Throws(IOException::class)
    fun execute(request: RequestModel): ResponseModel {
        state = State.RUNNING
        try {
            val httpRequest = Requests.createHttpRequest(request)
            httpCall = httpClient.newCall(httpRequest)
            val response = httpCall!!.execute()
            return ResponseModel(
                    response.code,
                    response.header("Transfer-Encoding", "") == "chunked",
                    getHeaders(response),
                    getContentType(response),
                    response.body)
        } finally {
        }
    }

    private fun getContentType(response: Response): String? {
        val header = response.header("Content-Type")
        if (header != null) {
            val semicolonIndex = header.indexOf(";")
            return if (semicolonIndex >= 0) {
                header.substring(0, semicolonIndex)
            } else {
                header
            }
        }
        return null
    }

    private fun getHeaders(response: Response): List<String> {
        val allHeaders = response.headers
        val result = ArrayList<String>()
        for ((first, second) in allHeaders) {
            result.add("$first: $second")
        }
        return result
    }

    companion object {

        private val EXECUTOR_KEY = Key<RequestExecutor>("ExecutorKey")

        private val DEFAULT_CHARSET = StandardCharsets.UTF_8

        fun getInstance(dataHolder: UserDataHolder): RequestExecutor {
            var executor = dataHolder.getUserData(EXECUTOR_KEY)
            if (executor == null) {
                executor = RequestExecutor()
                dataHolder.putUserData(EXECUTOR_KEY, executor)
            }
            return executor
        }
    }
}
