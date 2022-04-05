package com.pixplaze.keyword.util;

import java.util.Locale;

public class Common {
    public static Locale escapeLocale(String locale) {
        var languageTag = locale.replaceAll("_", "-");
        return Locale.forLanguageTag(languageTag);
    }
}
