package cn.vove7.plugin.rest.filetype

import com.intellij.psi.codeStyle.CodeStyleSettingsProvider
import com.intellij.psi.codeStyle.CodeStyleSettings
import com.intellij.application.options.CodeStyleAbstractConfigurable
import com.intellij.application.options.CodeStyleAbstractPanel
import cn.vove7.plugin.rest.filetype.RestCodeStyleSettingsProvider.RestTabbedLanguageCodeStylePanel
import com.intellij.psi.codeStyle.CustomCodeStyleSettings
import cn.vove7.plugin.rest.filetype.RestCodeStyleSettings
import com.intellij.application.options.TabbedLanguageCodeStylePanel
import cn.vove7.plugin.rest.filetype.RestLanguage
import com.intellij.openapi.options.Configurable

/**
 * @author danblack
 */
class RestCodeStyleSettingsProvider : CodeStyleSettingsProvider() {
    override fun createSettingsPage(settings: CodeStyleSettings, originalSettings: CodeStyleSettings): Configurable {
        return object : CodeStyleAbstractConfigurable(settings, originalSettings, "Rest") {
            override fun getHelpTopic(): String? {
                return null
            }

            override fun createPanel(settings: CodeStyleSettings): CodeStyleAbstractPanel {
                return RestTabbedLanguageCodeStylePanel(currentSettings, settings)
            }
        }
    }

    override fun getConfigurableDisplayName(): String? {
        return "Rest"
    }

    override fun createCustomSettings(settings: CodeStyleSettings): CustomCodeStyleSettings? {
        return RestCodeStyleSettings(settings)
    }

    private class RestTabbedLanguageCodeStylePanel(currentSettings: CodeStyleSettings?, settings: CodeStyleSettings?) :
        TabbedLanguageCodeStylePanel(RestLanguage, currentSettings, settings)
}