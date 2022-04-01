package com.pixplaze.keyword;

import com.pixplaze.exceptions.LanguageNotSupportedException;
import com.pixplaze.exceptions.EmptyDictionaryError;

import java.util.Locale;
import java.util.Set;


public class DefaultDictionary implements Dictionary {
    private final Set<Translation> translations;

    public DefaultDictionary(Set<Translation> translations) {
        this.translations = translations;
        if (translations.size() == 0) throw new EmptyDictionaryError();
    }

    @Override
    public String get(Locale locale, String keyword) throws LanguageNotSupportedException {
        for (var dict : this.translations) {
            if (dict.locale().equals(locale))
                return dict.get(keyword);
        }
        throw new LanguageNotSupportedException(locale);
    }
}
