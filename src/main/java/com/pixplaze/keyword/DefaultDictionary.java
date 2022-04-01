package com.pixplaze.keyword;

import com.pixplaze.keyword.exceptions.DictionaryError;
import com.pixplaze.keyword.exceptions.LanguageNotSupportedException;
import com.pixplaze.keyword.exceptions.EmptyDictionaryError;

import java.util.Locale;
import java.util.Set;


public class DefaultDictionary implements Dictionary {
    private final Set<Translation> translations;
    private Locale[] locales;
    private final Locale defaultLocale;

    public DefaultDictionary(Set<Translation> translations, Locale defaultLocale) {
        this.translations = translations;
        this.defaultLocale = defaultLocale;
        if (defaultLocale.toLanguageTag().equals("und") || defaultLocale.getCountry().isEmpty())
            throw new DictionaryError("Invalid default locale (%s)!".formatted(defaultLocale.toLanguageTag()));
        // TODO: Check if `defaultLocale` in `translations`
        if (translations.size() == 0) throw new EmptyDictionaryError();
    }

    @Override
    public String get(Locale locale, String keyword) throws LanguageNotSupportedException {
        Translation defaultTranslation = null;
        for (var translation : this.translations) {
            if (translation.locale().equals(locale))
                return translation.get(keyword);
            if (translation.locale().equals(this.defaultLocale))
                defaultTranslation = translation;
        }
        try {
            if (defaultTranslation == null) throw new Exception();
            return defaultTranslation.get(keyword);
        } catch (Exception e) {
            throw new LanguageNotSupportedException(locale);
        }
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
