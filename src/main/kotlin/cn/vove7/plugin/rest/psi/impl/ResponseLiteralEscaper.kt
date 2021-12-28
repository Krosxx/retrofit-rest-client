package cn.vove7.plugin.rest.psi.impl

import com.intellij.openapi.util.TextRange
import com.intellij.psi.LiteralTextEscaper

/**
 *
 */
class ResponseLiteralEscaper(host: RestElementImpl?) : LiteralTextEscaper<RestElementImpl?>(host!!) {
    override fun decode(rangeInsideHost: TextRange, outChars: StringBuilder): Boolean {
        outChars.append(myHost!!.text, rangeInsideHost.startOffset, rangeInsideHost.endOffset)
        return true
    }

    override fun getOffsetInHost(offsetInDecoded: Int, rangeInsideHost: TextRange): Int {
        var offset = offsetInDecoded + rangeInsideHost.startOffset
        if (offset < rangeInsideHost.startOffset) offset = rangeInsideHost.startOffset
        if (offset > rangeInsideHost.endOffset) offset = rangeInsideHost.endOffset
        return offset
    }

    override fun isOneLine(): Boolean {
        return true
    }
}