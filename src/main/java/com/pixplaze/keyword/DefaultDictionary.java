package com.pixplaze.keyword;

import com.pixplaze.exceptions.NoLanguageException;

import java.util.Locale;
import java.util.Set;

public class DefaultDictionary implements Dictionary {
    private final Set<Translation> translations;
    public DefaultDictionary(Set<Translation> translations) {
        this.translations = translations;
    }
    @Override
    public String get(Locale locale, String keyword) {

        for (var dict: this.translations) {
            if (dict.locale().equals(locale))
                return dict.get(keyword);
        }
        throw new NoLanguageException(locale);
    }
}
