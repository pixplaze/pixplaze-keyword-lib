package com.pixplaze.exceptions;

import java.util.Locale;

public class NoLanguageException extends RuntimeException {
    public NoLanguageException(Locale locale) {
        super("No translation for language " + locale.getDisplayLanguage(Locale.US));
    }
}
