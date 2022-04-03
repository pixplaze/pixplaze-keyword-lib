package com.pixplaze.keyword;

import org.bukkit.ChatColor;

import java.util.Locale;
import java.util.Set;

public class SpigotDictionary extends DefaultDictionary {
    public SpigotDictionary(Set<Translation> translations, Locale defaultLocale) {
        super(translations, defaultLocale);
    }

    @Override
    public String get(Locale locale, String keyword) {
        var value = super.get(locale, keyword);
        value = ChatColor.translateAlternateColorCodes('&', value);
        return value;
    }
}
