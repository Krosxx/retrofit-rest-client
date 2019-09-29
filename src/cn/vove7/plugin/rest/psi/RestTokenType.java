package cn.vove7.plugin.rest.psi;

import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import cn.vove7.plugin.rest.filetype.RestLanguage;

/**
 *
 */
public class RestTokenType extends IElementType {
    public RestTokenType(@NotNull @NonNls String debugName) {
        super(debugName, RestLanguage.INSTANCE);
    }
}
