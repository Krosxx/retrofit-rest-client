package cn.vove7.plugin.rest.filetype

import com.intellij.openapi.components.ApplicationComponent

/**
 * @author danblack
 */
class RestClientPlugin : ApplicationComponent {
    override fun getComponentName(): String {
        return "Rest client plugin"
    }

    override fun initComponent() {}
    override fun disposeComponent() {}
}