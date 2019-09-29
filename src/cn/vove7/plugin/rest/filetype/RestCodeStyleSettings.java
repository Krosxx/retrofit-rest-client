package cn.vove7.plugin.rest.filetype;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;

/**
 * @author danblack
 */
public class RestCodeStyleSettings extends CustomCodeStyleSettings {
    protected RestCodeStyleSettings(CodeStyleSettings container) {
        super("RestCodeStyleSettings", container);
    }
}
