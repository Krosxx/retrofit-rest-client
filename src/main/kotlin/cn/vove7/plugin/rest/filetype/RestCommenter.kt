package cn.vove7.plugin.rest.filetype

import com.intellij.lang.Commenter

/**
 * @author danblack
 */
class RestCommenter : Commenter {
    override fun getLineCommentPrefix(): String? {
        return "#"
    }

    override fun getBlockCommentPrefix(): String? {
        return null
    }

    override fun getBlockCommentSuffix(): String? {
        return null
    }

    override fun getCommentedBlockCommentPrefix(): String? {
        return null
    }

    override fun getCommentedBlockCommentSuffix(): String? {
        return null
    }
}