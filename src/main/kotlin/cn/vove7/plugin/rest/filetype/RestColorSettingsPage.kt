package cn.vove7.plugin.rest.filetype

import com.intellij.openapi.options.colors.ColorSettingsPage
import com.intellij.openapi.options.colors.AttributesDescriptor
import cn.vove7.plugin.rest.filetype.RestColorSettingsPage
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import cn.vove7.plugin.rest.filetype.RestHighlighter
import com.intellij.openapi.editor.colors.TextAttributesKey
import javax.swing.Icon

/**
 * @author danblack
 */
class RestColorSettingsPage : ColorSettingsPage {
    override fun getAttributeDescriptors(): Array<AttributesDescriptor> {
        return DESCRIPTORS
    }

    override fun getColorDescriptors(): Array<ColorDescriptor> {
        return ColorDescriptor.EMPTY_ARRAY
    }

    override fun getDisplayName(): String {
        return "Rest"
    }

    override fun getIcon(): Icon? {
        return null
    }

    override fun getHighlighter(): SyntaxHighlighter {
        return RestHighlighter()
    }

    override fun getDemoText(): String {
        return """
            # This is a comment
            -- option = value
            POST
            http://site.com
            &param=value
            @Content-type: application/xml
            <body>
            This is a request body
            </body>
            %%%
            @Content-length: 100
            <response>
            This is a response body
            </response>
            """.trimIndent()
    }

    override fun getAdditionalHighlightingTagToDescriptorMap(): Map<String, TextAttributesKey>? {
        return null
    }

    companion object {
        private val DESCRIPTORS = arrayOf(
            AttributesDescriptor("Comment", RestHighlighter.COMMENT),
            AttributesDescriptor("Method", RestHighlighter.METHOD),
            AttributesDescriptor("Url", RestHighlighter.URL),
            AttributesDescriptor("Header", RestHighlighter.HEADER),
            AttributesDescriptor("Option", RestHighlighter.OPTION),
            AttributesDescriptor("Param", RestHighlighter.PARAM),
            AttributesDescriptor("Error", RestHighlighter.BAD_CHARACTER),
            AttributesDescriptor("Separator", RestHighlighter.SEPARATOR),
            AttributesDescriptor("Body", RestHighlighter.BODY)
        )
    }
}