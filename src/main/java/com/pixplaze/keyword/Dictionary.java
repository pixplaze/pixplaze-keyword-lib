package com.pixplaze.keyword;

import com.pixplaze.exceptions.NoTranslationSpecifiedException;
import com.pixplaze.parser.TranslationParser;

import java.util.Locale;

public interface Dictionary {
    String get(Locale locale, String keyword) throws NoTranslationSpecifiedException;
}
