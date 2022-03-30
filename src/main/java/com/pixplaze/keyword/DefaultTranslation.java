package com.pixplaze.keyword;

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
        return this.translation.get(keyword);
    }
}
