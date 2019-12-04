package cn.vove7.plugin.rest.tool

import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.intellij.openapi.project.Project
import java.io.File
import java.lang.ref.WeakReference


/**
 * # EnvConfig
 * Created by 11324.
 * Date: 2019/9/27
 */
class EnvConfig(private val project: Project) {
    private val fileName = "rest-client/rest_env.json"

    private val envFile by lazy {
        File(project.basePath + "/.idea", fileName)
    }
    @Suppress("UNCHECKED_CAST")
    private val envMap: Map<String, JsonObject>
        get() {
            val map = hashMapOf<String, JsonObject>()
            val j = JsonParser().parse(envFile.readText()) as JsonObject
            j.keySet().forEach {
                map[it] = j.get(it) as JsonObject
            }
            return map
        }
//        = Gson().fromJson(envFile.readText(), Map::class.java) as Map<String, Map<String, String>>

    private var envCache: WeakReference<Map<String, JsonObject>>? = null
    fun allEnv(): List<String> {
        return if (hasConfig()) {
            val e = envMap
            envCache = WeakReference(e)
            e.keys.toList()
        } else {
            emptyList()
        }
    }

    fun hasConfig(): Boolean {
        return envFile.exists()
    }

    fun getEnv(name: String): JsonObject? {
        if (hasConfig() || envCache != null) {
            return (envCache?.get() ?: envMap)[name]
            envCache = null
        }
        return null
    }
}

typealias KV = Map<String, String>
