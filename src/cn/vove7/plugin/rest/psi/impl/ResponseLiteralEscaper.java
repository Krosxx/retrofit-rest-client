package cn.vove7.plugin.rest.psi.impl;

import com.intellij.openapi.util.TextRange;
import com.intellij.psi.LiteralTextEscaper;
import org.jetbrains.annotations.NotNull;

/**
 *
 */
public class ResponseLiteralEscaper extends LiteralTextEscaper<RestElementImpl> {
    public ResponseLiteralEscaper(RestElementImpl host) {
        super(host);
    }

    @Override
    public boolean decode(@NotNull final TextRange rangeInsideHost, @NotNull StringBuilder outChars) {
        outChars.append(myHost.getText(), rangeInsideHost.getStartOffset(), rangeInsideHost.getEndOffset());
        return true;
    }

    @Override
    public int getOffsetInHost(int offsetInDecoded, @NotNull final TextRange rangeInsideHost) {
        int offset = offsetInDecoded + rangeInsideHost.getStartOffset();
        if (offset < rangeInsideHost.getStartOffset()) offset = rangeInsideHost.getStartOffset();
        if (offset > rangeInsideHost.getEndOffset()) offset = rangeInsideHost.getEndOffset();
        return offset;
    }

    @Override
    public boolean isOneLine() {
        return true;
    }
}
