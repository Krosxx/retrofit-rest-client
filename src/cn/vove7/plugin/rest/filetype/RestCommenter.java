package cn.vove7.plugin.rest.filetype;

import com.intellij.lang.Commenter;
import org.jetbrains.annotations.Nullable;

/**
 * @author danblack
 */
public class RestCommenter implements Commenter {
    @Nullable
    @Override
    public String getLineCommentPrefix() {
        return "#";
    }

    @Nullable
    @Override
    public String getBlockCommentPrefix() {
        return null;
    }

    @Nullable
    @Override
    public String getBlockCommentSuffix() {
        return null;
    }

    @Nullable
    @Override
    public String getCommentedBlockCommentPrefix() {
        return null;
    }

    @Nullable
    @Override
    public String getCommentedBlockCommentSuffix() {
        return null;
    }
}
