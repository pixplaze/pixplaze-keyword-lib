package com.pixplaze.keyword;

import com.pixplaze.keyword.exceptions.LanguageNotSupportedException;
import com.pixplaze.keyword.exceptions.EmptyDictionaryError;

import java.util.Locale;
import java.util.Set;


public class DefaultDictionary implements Dictionary {
    private final Set<Translation> translations;
    private Locale[] locales;

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

    @Override
    public Locale[] getLocales() {
        if (locales == null) {
            locales = new Locale[translations.size()];
            var iter = translations.iterator();
            for (var i = 0; iter.hasNext(); i++) {
                var item = iter.next();
                locales[i] = item.locale();
            }
        }
        return locales;
    }

}
