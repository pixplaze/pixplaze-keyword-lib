package com.pixplaze.keyword;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Locale;

public class Test {
    public static String test() {
        return "pidoras";
    }

    public static void main(String[] args) {
        var ruMap = new HashMap<String, String>();
        ruMap.put("error_message", "Ошибка!");
        ruMap.put("warning_message", "Предупреждение...");
        ruMap.put("success_message", "Успех!");

        var ruTranslation = new DefaultTranslation(Locale.forLanguageTag("ru-RU"), ruMap);

        var set = new HashSet<Translation>();
        set.add(ruTranslation);

        var dict = new DefaultDictionary(set);

        System.out.println(Locale.forLanguageTag("ru-RU").getDisplayName(Locale.US));
        // System.out.println(dict.get(Locale.forLanguageTag("ru-RU"), "error_message"));

    }
}
