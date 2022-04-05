package com.pixplaze.keyword.exceptions;

import java.util.Locale;

public class LanguageNotSupportedException extends RuntimeException {
    public LanguageNotSupportedException(Locale locale) {
        super(
                locale.toLanguageTag().equals("und") ?
                        "Undefined language!" :
                        "Language" + (locale.getDisplayLanguage(Locale.US).isEmpty() ?
                                "" : " %s".formatted(locale.getDisplayLanguage(Locale.US))) +
                        " %s".formatted(locale.toLanguageTag()) +  " is not supported!"
        );
    }
}
