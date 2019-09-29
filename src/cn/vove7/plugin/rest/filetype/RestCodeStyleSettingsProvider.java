package cn.vove7.plugin.rest.filetype;

import com.intellij.application.options.CodeStyleAbstractConfigurable;
import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.openapi.options.Configurable;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/**
 * @author danblack
 */
public class RestCodeStyleSettingsProvider extends CodeStyleSettingsProvider {
    @NotNull
    @Override
    public Configurable createSettingsPage(CodeStyleSettings settings, CodeStyleSettings originalSettings) {
        return new CodeStyleAbstractConfigurable(settings, originalSettings, "Rest") {

            @Nullable
            @Override
            public String getHelpTopic() {
                return null;
            }

            @Override
            protected CodeStyleAbstractPanel createPanel(CodeStyleSettings settings) {
                return new RestTabbedLanguageCodeStylePanel(getCurrentSettings(), settings);
            }
        };
    }

    @Nullable
    @Override
    public String getConfigurableDisplayName() {
        return "Rest";
    }

    @Nullable
    @Override
    public CustomCodeStyleSettings createCustomSettings(CodeStyleSettings settings) {
        return new RestCodeStyleSettings(settings);
    }

    private static class RestTabbedLanguageCodeStylePanel extends TabbedLanguageCodeStylePanel {

        protected RestTabbedLanguageCodeStylePanel(CodeStyleSettings currentSettings, CodeStyleSettings settings) {
            super(RestLanguage.INSTANCE, currentSettings, settings);
        }
    }
}
