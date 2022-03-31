package com.pixplaze.keyword;

import com.pixplaze.exceptions.LanguageNotSupportedException;

import java.util.Locale;

public interface Dictionary {
    String get(Locale locale, String keyword) throws LanguageNotSupportedException;
}
