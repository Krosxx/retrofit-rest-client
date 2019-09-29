package cn.vove7.plugin.rest.model

import cn.vove7.plugin.rest.tool.decodeUrl
import cn.vove7.plugin.rest.tool.getFormattedResponse
import cn.vove7.plugin.rest.tool.joinToString
import com.google.gson.Gson
import com.intellij.openapi.project.Project
import java.text.SimpleDateFormat
import java.util.*

class RequestModel {
    var method: Method? = null
    var url: String
    val headers: MutableMap<String, String>
    val params: MutableMap<String, String>
    var body: String? = null

    //仅在解析retrofit api时使用
    val queries: MutableMap<String, String> = mutableMapOf()
    val bodyFields: MutableMap<String, String> = mutableMapOf()
    val urlPaths: MutableList<String> = mutableListOf()
    //------------------

    enum class Method {
        GET,
        POST,
        PUT,
        PATCH,
        DELETE
    }

    constructor() {
        url = ""
        headers = mutableMapOf()
        params = mutableMapOf()
    }

    constructor(method: Method, url: String, headers: MutableMap<String, String>,
                params: MutableMap<String, String>, body: String?) {
        this.method = method
        this.url = url
        this.headers = headers
        this.params = params
        this.body = body
    }

    /**
     * 转rest文件内容
     */
    fun toRestFileContent(project: Project): String = buildString {
        appendln(method.toString())
        appendln(url.decodeUrl())
        queries.forEach { (t, u) ->
            appendln("&$t=$u")
        }
        appendln()
        if (headers.isNotEmpty()) {
            appendln("# Headers")
        }
        appendln(headers.joinToString("\n", "", "") { t, u -> "@$t: $u" })

        appendln(params.joinToString("&", "", "") { t, u -> "$t=$u" })
        var hasBody = false
        if (bodyFields.isNotEmpty()) {
            appendln("# Body")
            hasBody = true
            val text = getFormattedResponse(project, "application/json", Gson().toJson(bodyFields))
            appendln(text)
        }
        if (!body.isNullOrBlank()) {
            if (!hasBody) appendln("# Body")
            appendln(body)
        }
    }

    //存在未赋值参数 {}
    fun hasUnfilledParam(): Boolean {
        if (placeholderParamRegex.find(url) != null) return true
        headers.values.forEach {
            if (placeholderParamRegex.find(it) != null) return true
        }
        params.values.forEach {
            if (placeholderParamRegex.find(it) != null) return true
        }
        if (placeholderParamRegex.find(body ?: "") != null) return true

        return false
    }

    private val placeholderParamRegex by lazy { "\\{([0-9a-zA-Z_:#-]*)\\}".toRegex() }

    //find {}
    //url
    //query
    //header
    //param
    fun fill(env: Map<String, Any>) {
        replaceString(url, env) { p, v ->
            url = url.replace(p, v)
        }
        replaceMap(headers, env)
        replaceMap(params, env)

        body?.also { b ->
            replaceString(b, env) { p, v ->
                body = body!!.replace(p, v)
            }
        }
    }

    //替换String 类型
    private fun replaceString(s: String, env: Map<String, Any>, onReplace: (p: String, v: String) -> Unit) {
        placeholderParamRegex.findAll(s).forEach {
            val p = it.groupValues[1]
            if (p in env) {
                onReplace(it.value, env[p].toString())
            } else {
                checkSupport(p)?.also { v ->
                    onReplace(it.value, v)
                }
            }
        }

    }

    //替换Map类型
    private fun replaceMap(map: MutableMap<String, String>, env: Map<String, Any>) {
        map.forEach { (t, u) ->
            if (u.startsWith('{') && u.endsWith('}')) {
                val pname = u.substring(1 until u.length-1)
                if (pname in env) {
                    map[t] = env[pname] as String
                } else {
                    checkSupport(pname)?.also { v ->
                        map[t] = v
                    }
                }
            }
        }
    }

    /**
     * 内部支持参数名
     * 1.时间戳
     *
     * @return String
     */
    private fun checkSupport(paramName: String): String? {
        if (paramName.startsWith("ts", true)) {
            return formatTimeStamp(paramName)
        }
        return null
    }

    /**
     * 时间戳 格式：
     * TS_SEC（秒）, TS（毫秒）, TS_YYYY-MM-dd#HH:mm:ss （自定义pattern #代替空格）
     *
     * @param paramName String
     * @return String
     */
    private fun formatTimeStamp(paramName: String): String {
        return if (paramName.length == 2) {//毫秒
            val s = System.currentTimeMillis()
            return s.toString()
        } else {
            val tsPattern = paramName.substring(3)
            if (tsPattern.equals("sec", true)) {//秒
                val s = System.currentTimeMillis() / 1000
                s.toString()
            } else {//指定格式
                val df = SimpleDateFormat(tsPattern.replace('#', ' '))
                df.format(Date())
            }
        }
    }
}
