package com.pixplaze.parser;

import com.pixplaze.exceptions.parsing.IllegalLocaleFormatException;
import com.pixplaze.exceptions.parsing.KeywordsNotSpecifiedException;
import com.pixplaze.exceptions.parsing.LocaleNotSpecifiedException;
import com.pixplaze.exceptions.parsing.TranslationNotSpecifiedException;
import com.pixplaze.keyword.DefaultTranslation;
import com.pixplaze.keyword.Translation;
import org.bukkit.configuration.file.FileConfiguration;

import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class YamlParser implements TranslationParser<FileConfiguration> {

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

        var keywords = translation.getConfigurationSection("keywords");
        if (keywords == null) throw new KeywordsNotSpecifiedException();

        var locale = Locale.forLanguageTag(language);
        if (locale.toLanguageTag().equals("und")) throw new IllegalLocaleFormatException();


        var map = keywords.getValues(true);

        map.remove(language);
        var lang = map.entrySet().stream().collect(
                Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue())
        );
        return new DefaultTranslation(locale, lang);
    }
}
