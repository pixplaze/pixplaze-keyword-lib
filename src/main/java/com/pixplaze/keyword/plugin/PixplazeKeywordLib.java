package com.pixplaze.keyword.plugin;

import com.pixplaze.keyword.DefaultDictionary;
import com.pixplaze.keyword.Dictionary;
import com.pixplaze.keyword.Translation;
import com.pixplaze.keyword.exceptions.parsing.*;
import com.pixplaze.keyword.parser.TranslationParser;
import com.pixplaze.keyword.parser.YamlTranslationParser;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Set;

public class PixplazeKeywordLib extends JavaPlugin {

    public static Dictionary fromLanguageDirectory(File dir) throws ParsingError {
        return fromLanguageDirectory(dir, "");
    }

    public static Dictionary fromLanguageDirectory(File dir, String fileExtension) throws ParsingError {
        return fromLanguageDirectory(DefaultDictionary.class, new YamlTranslationParser(), dir, fileExtension);
    }

    public static <R> Dictionary fromLanguageDirectory(
            Class<? extends Dictionary> dictionaryType,
            TranslationParser<R> parser, File dir,
            String fileExtension) throws ParsingError
    {
        var set = new HashSet<Translation>();
        if (dir != null && dir.isDirectory()) {
            var files = dir.listFiles(file -> file.getName().endsWith(fileExtension));

            assert files != null;
            for (var file : files) {
                try {
                    var parsingInstance = parser.load(file);
                    set.add(parser.parse(parsingInstance));
                } catch (
                        TranslationNotSpecifiedException |
                        LocaleNotSpecifiedException |
                        KeywordsNotSpecifiedException |
                        IllegalLocaleFormatException e)
                {
                    throw new ParsingError("%s%nFile: %s".formatted(e.getMessage(), file.getPath()));
                }
            }
        }
        try {
            var constructor = dictionaryType.getConstructor(Set.class);
            return constructor.newInstance(set);
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
            throw new ParsingError("Could not load dictionary %s".formatted(dictionaryType.getCanonicalName()));
        }
    }
}
