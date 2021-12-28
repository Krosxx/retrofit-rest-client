package cn.vove7.plugin.rest.psi

import com.intellij.psi.tree.IElementType
import cn.vove7.plugin.rest.filetype.RestLanguage
import org.jetbrains.annotations.NonNls

/**
 *
 */
class RestTokenType(debugName: @NonNls String) : IElementType(debugName, RestLanguage)