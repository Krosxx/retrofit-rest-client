package cn.vove7.plugin.rest.filetype;

import com.intellij.openapi.components.ApplicationComponent;
import org.jetbrains.annotations.NotNull;

/**
 * @author danblack
 */
public class RestClientPlugin implements ApplicationComponent {

    @NotNull
    @Override
    public String getComponentName() {
        return "Rest client plugin";
    }

    @Override
    public void initComponent() {
    }

    @Override
    public void disposeComponent() {
    }
}
