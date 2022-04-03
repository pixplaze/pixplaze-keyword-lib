package com.pixplaze.keyword;

import com.pixplaze.keyword.exceptions.NoKeywordDefinedException;

import java.util.Locale;
import java.util.Map;

public class DefaultTranslation implements Translation {

    private final Locale locale;
    private final Map<String, String> translation;

    public DefaultTranslation(Locale locale, Map<String, String> translation) {
        this.locale = locale;
        this.translation = translation;
    }

    @Override
    public Locale locale() {
        return this.locale;
    }

    @Override
    public String get(String keyword) {
        var value = this.translation.get(keyword);
        if (value == null) throw new NoKeywordDefinedException(this.locale, keyword);
        return value;
    }


}
