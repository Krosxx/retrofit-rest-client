package cn.vove7.plugin.rest.filetype;

import com.intellij.lang.Language;
import com.intellij.lang.injection.MultiHostInjector;
import com.intellij.lang.injection.MultiHostRegistrar;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLanguageInjectionHost;
import com.intellij.psi.impl.source.tree.injected.MultiHostRegistrarImpl;

import org.jetbrains.annotations.NotNull;

import cn.vove7.plugin.rest.psi.*;
import cn.vove7.plugin.rest.psi.impl.RestElementImpl;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 *
 */
public class RestHostInjector implements MultiHostInjector {
    @Override
    public void getLanguagesToInject(@NotNull MultiHostRegistrar registrar, @NotNull final PsiElement context) {
        final MultiHostRegistrarImpl r = (MultiHostRegistrarImpl) registrar;
        RestHeaders headers = null;
        if (context instanceof RestResponseBody) {
            headers = ((RestResponse) context.getParent()).getHeaders();
        } else if (context instanceof RestRequestBody) {
            headers = ((RestRequest) context.getParent()).getHeaders();
        }
        //OOM  1 char 2 bytes  2M = 2<<20
        if (headers != null && context.getTextRange().getLength() < (1 << 20)) {
            String contentType = getContentType(headers);
            if (contentType != null) {
                Collection<Language> langList = Language.findInstancesByMimeType(contentType);
                if (!langList.isEmpty()) {
                    r.startInjecting(langList.iterator().next())
                            .addPlace(null, null, (PsiLanguageInjectionHost) context, TextRange.create(0, context.getTextLength()))
                            .doneInjecting();
                }
            }
        }
    }

    private boolean isThunked(@NotNull RestHeaders headers) {
        return "chunked".equals(getHeader(headers, "Transfer-Encoding"));
    }

    private String getContentType(@NotNull RestHeaders headers) {
        return getHeader(headers, "Content-Type");
    }

    private String getHeader(@NotNull RestHeaders headers, String key) {
        if (headers.isValid()) {
            for (RestEHeader eHeader : headers.getEHeaderList()) {
                String header = eHeader.getText();
                if (header.startsWith("@" + key)) {
                    int colonIndex = header.indexOf(":");
                    if (colonIndex >= 0) {
                        String contentType = header.substring(colonIndex + 1);
                        int semicolonIndex = contentType.indexOf(";");
                        if (semicolonIndex >= 0) {
                            return contentType.substring(0, semicolonIndex).trim();
                        }
                        return contentType.trim();
                    }
                }
            }
        }
        return null;
    }


    @NotNull
    @Override
    public List<? extends Class<? extends PsiElement>> elementsToInjectIn() {
        return Collections.singletonList(RestElementImpl.class);
    }
}
