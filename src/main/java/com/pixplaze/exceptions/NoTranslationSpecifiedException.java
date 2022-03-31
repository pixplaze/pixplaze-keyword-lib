package com.pixplaze.exceptions;

import java.util.Locale;

public class NoTranslationSpecifiedException extends RuntimeException {
    public NoTranslationSpecifiedException(Locale locale) {
        super("No translation for language " + locale.getDisplayLanguage(Locale.US));
    }
}
