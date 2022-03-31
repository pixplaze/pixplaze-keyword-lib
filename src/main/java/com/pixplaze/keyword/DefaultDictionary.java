package com.pixplaze.keyword;

import com.pixplaze.exceptions.NoTranslationSpecifiedException;
import com.pixplaze.parser.TranslationParser;
import com.pixplaze.parser.YamlParser;
import org.bukkit.configuration.InvalidConfigurationException;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class DefaultDictionary implements Dictionary {
    private final Set<Translation> translations;

    public DefaultDictionary(Set<Translation> translations) {
        this.translations = translations;
    }

    public DefaultDictionary(YamlParser parser, File dir) {
        var set = new HashSet<Translation>();
        if (dir != null && dir.isDirectory()) {
            var files = dir.listFiles(file -> file.getName().endsWith(".yml")); // ??
            var yml = new YamlConfiguration();

            assert files != null;
            for (var file: files) {
                try {
                    yml.load(file);
                    set.add(parser.parse(yml));
                } catch (IOException | InvalidConfigurationException e) {
                    e.printStackTrace();
                }
            }
        }
        this.translations = set;
    }

    @Override
    public String get(Locale locale, String keyword) throws NoTranslationSpecifiedException {
        for (var dict: this.translations) {
            if (dict.locale().equals(locale))
                return dict.get(keyword);
        }
        throw new NoTranslationSpecifiedException(locale);
    }
}
