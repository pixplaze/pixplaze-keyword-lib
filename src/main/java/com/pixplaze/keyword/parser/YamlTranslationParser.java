package com.pixplaze.keyword.parser;

import com.pixplaze.keyword.DefaultTranslation;
import com.pixplaze.keyword.Translation;
import com.pixplaze.keyword.exceptions.parsing.*;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class YamlTranslationParser implements TranslationParser<FileConfiguration> {

    @Override
    public Translation parse(FileConfiguration yml) throws
            TranslationNotSpecifiedException,
            LocaleNotSpecifiedException,
            KeywordsNotSpecifiedException,
            IllegalLocaleFormatException {

        var translation = yml.getConfigurationSection("translation");
        if (translation == null) throw new TranslationNotSpecifiedException();

        var language = translation.getString("locale");
        if (language == null) throw new LocaleNotSpecifiedException();

        var locale = Locale.forLanguageTag(language);
        if (locale.toLanguageTag().equals("und")) throw new IllegalLocaleFormatException();

        var keywords = translation.getConfigurationSection("keywords");
        if (keywords == null) throw new KeywordsNotSpecifiedException();

        var map = keywords.getValues(true);

        var lang = map.entrySet().stream().collect(
                Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue())
        );
        return new DefaultTranslation(locale, lang);
    }

    @Override
    public FileConfiguration load(Object file) {
        try {
            return YamlConfiguration.loadConfiguration((File) file);
        } catch (Exception e) {
            throw new ParsingError();
        }
    }
}
