package com.pixplaze.keyword.exceptions;

import java.util.Locale;

public class LanguageNotSupportedException extends RuntimeException {
    public LanguageNotSupportedException(Locale locale) {
        super("%s (%s) language is not supported!".formatted(locale.getDisplayLanguage(Locale.US), locale.toLanguageTag()));
    }
}
