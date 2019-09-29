package cn.vove7.plugin.rest.filetype;

import com.intellij.openapi.editor.colors.TextAttributesKey;
import com.intellij.openapi.fileTypes.SyntaxHighlighter;
import com.intellij.openapi.options.colors.AttributesDescriptor;
import com.intellij.openapi.options.colors.ColorDescriptor;
import com.intellij.openapi.options.colors.ColorSettingsPage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;
import java.util.Map;

/**
 * @author danblack
 */
public class RestColorSettingsPage implements ColorSettingsPage {

    private static final AttributesDescriptor[] DESCRIPTORS = new AttributesDescriptor[]{
            new AttributesDescriptor("Comment", RestHighlighter.COMMENT),
            new AttributesDescriptor("Method", RestHighlighter.METHOD),
            new AttributesDescriptor("Url", RestHighlighter.URL),
            new AttributesDescriptor("Header", RestHighlighter.HEADER),
            new AttributesDescriptor("Option", RestHighlighter.OPTION),
            new AttributesDescriptor("Param", RestHighlighter.PARAM),
            new AttributesDescriptor("Error", RestHighlighter.BAD_CHARACTER),
            new AttributesDescriptor("Separator", RestHighlighter.SEPARATOR),
            new AttributesDescriptor("Body", RestHighlighter.BODY),
    };

    @NotNull
    @Override
    public AttributesDescriptor[] getAttributeDescriptors() {
        return DESCRIPTORS;
    }

    @NotNull
    @Override
    public ColorDescriptor[] getColorDescriptors() {
        return ColorDescriptor.EMPTY_ARRAY;
    }

    @NotNull
    @Override
    public String getDisplayName() {
        return "Rest";
    }

    @Nullable
    @Override
    public Icon getIcon() {
        return null;
    }

    @NotNull
    @Override
    public SyntaxHighlighter getHighlighter() {
        return new RestHighlighter();
    }

    @NotNull
    @Override
    public String getDemoText() {
        return "# This is a comment\n" +
                "-- option = value\n" +
                "POST\n" +
                "http://site.com\n" +
                "&param=value\n" +
                "@Content-type: application/xml\n" +
                "<body>\n" +
                "This is a request body\n" +
                "</body>\n" +
                "%%%\n" +
                "@Content-length: 100\n" +
                "<response>\n" +
                "This is a response body\n" +
                "</response>";
    }

    @Nullable
    @Override
    public Map<String, TextAttributesKey> getAdditionalHighlightingTagToDescriptorMap() {
        return null;
    }
}
