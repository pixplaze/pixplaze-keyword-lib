package com.pixplaze.exceptions;

import java.util.Locale;

public class KeywordNotSpecifiedException extends RuntimeException {
    public KeywordNotSpecifiedException(Locale locale, String keyword) {
        super("The \"%s\" keyword not specified for language \"%s\"".formatted(keyword, locale.getDisplayName(Locale.US)));
    }
}
