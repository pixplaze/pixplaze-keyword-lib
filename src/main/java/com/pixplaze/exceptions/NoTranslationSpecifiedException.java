package com.pixplaze.exceptions;

import java.util.Locale;

public class NoTranslationSpecifiedException extends RuntimeException {
    public NoTranslationSpecifiedException(Locale locale) {
        super("No translation for language %s (%s)".formatted(locale.getDisplayLanguage(Locale.US), locale.toLanguageTag()));
    }
}
