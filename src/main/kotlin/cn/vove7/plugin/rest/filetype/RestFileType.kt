package cn.vove7.plugin.rest.filetype

import com.intellij.openapi.fileTypes.LanguageFileType
import cn.vove7.plugin.rest.filetype.RestLanguage
import com.intellij.icons.AllIcons
import cn.vove7.plugin.rest.filetype.RestFileType
import javax.swing.Icon

/**
 *
 */
class RestFileType : LanguageFileType(RestLanguage) {
    override fun getName(): String {
        return "Rest file"
    }

    override fun getDescription(): String {
        return "Rest language file"
    }

    override fun getDefaultExtension(): String {
        return "rest"
    }

    override fun getIcon(): Icon {
        return AllIcons.General.Web
    }

    companion object {
        @JvmField
        val INSTANCE: LanguageFileType = RestFileType()
    }
}