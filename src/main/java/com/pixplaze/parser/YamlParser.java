package com.pixplaze.parser;

import com.pixplaze.exceptions.parsing.KeywordsNotSpecifiedException;
import com.pixplaze.exceptions.parsing.LanguageNotSpecifiedException;
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
            LanguageNotSpecifiedException,
            KeywordsNotSpecifiedException {

        var translation = yml.getConfigurationSection("translation");
        if (translation == null) throw new TranslationNotSpecifiedException();

        var language = translation.getString("locale");
        if (language == null) throw new LanguageNotSpecifiedException();

        var keywords = translation.getConfigurationSection("keywords");
        if (keywords == null) throw new KeywordsNotSpecifiedException();

        var locale = Locale.forLanguageTag(language);
        var map = keywords.getValues(true);

        map.remove(language);
        return new DefaultTranslation(
                locale,
                map.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, e -> (String) e.getValue()))
        );
    }
}
