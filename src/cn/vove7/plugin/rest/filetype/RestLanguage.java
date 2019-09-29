package cn.vove7.plugin.rest.filetype;

import com.intellij.lang.Language;

/**
 *
 */
public class RestLanguage extends Language {

    public static final Language INSTANCE = new RestLanguage();

    public RestLanguage() {
        super("Rest");
    }
}
