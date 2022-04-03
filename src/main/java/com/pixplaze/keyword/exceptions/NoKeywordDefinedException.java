package com.pixplaze.keyword.exceptions;

import java.util.Locale;

public class NoKeywordDefinedException extends RuntimeException {
    public NoKeywordDefinedException(Locale locale, String keyword) {
        super("The \"%s\" keyword is not defined for language %s.".formatted(keyword, locale.getDisplayName(Locale.US)));
    }
}
