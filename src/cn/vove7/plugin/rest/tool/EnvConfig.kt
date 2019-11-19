package cn.vove7.plugin.rest.tool

import com.google.gson.Gson
import com.intellij.openapi.project.Project
import java.io.File


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
    private val envMap: Map<String, Map<String, String>>
        get() = Gson().fromJson(envFile.readText(), Map::class.java) as Map<String, Map<String, String>>


    fun allEnv(): List<String> {
        return envMap.keys.toList()
    }

    fun hasConfig(): Boolean {
        return envFile.exists()
    }

    fun getEnv(name: String): Map<String, Any> {
        if (hasConfig()) {
            return envMap[name, emptyMap()]
        }
        return emptyMap()
    }
}

typealias KV = Map<String, String>