package com.pixplaze.keyword.plugin;

import com.pixplaze.keyword.Dictionary;
import com.pixplaze.keyword.SpigotDictionary;
import com.pixplaze.keyword.Translation;
import com.pixplaze.keyword.exceptions.DictionaryError;
import com.pixplaze.keyword.exceptions.parsing.*;
import com.pixplaze.keyword.parser.TranslationParser;
import com.pixplaze.keyword.parser.YamlTranslationParser;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

public class PixplazeKeywordLib extends JavaPlugin {

    public static Dictionary fromLanguageDirectory(
            Locale defaultLocale,
            String languageFilesPath) throws ParsingError
    {
        return fromLanguageDirectory(defaultLocale, languageFilesPath, "");
    }

    public static Dictionary fromLanguageDirectory(
            Locale defaultLocale,
            String languageFilesPath,
            String languageFilesExtension) throws ParsingError
    {
        return fromLanguageDirectory(
                SpigotDictionary.class,
                new YamlTranslationParser(),
                defaultLocale,
                languageFilesPath,
                languageFilesExtension);
    }

    public static <R> Dictionary fromLanguageDirectory(
            Class<? extends Dictionary> dictionaryType,
            TranslationParser<R> parser,
            Locale defaultLocale,
            String languageFilesPath,
            String languageFilesExtension) throws ParsingError
    {
        var set = new HashSet<Translation>();
        var dir = new File(languageFilesPath);

        if (!dir.isDirectory())
            throw new DictionaryError("Not a directory! Path: %s".formatted(languageFilesPath));

        var files = dir.listFiles(file -> file.getName().endsWith(languageFilesExtension));
        if (files == null || files.length == 0)
            throw new DictionaryError("No language files in %s".formatted(languageFilesPath));

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
        try {
            var constructor = dictionaryType.getConstructor(Set.class, Locale.class);
            return constructor.newInstance(set, defaultLocale);
        } catch (
                 InstantiationException |
                 IllegalAccessException |
                 InvocationTargetException |
                 NoSuchMethodException e) {
            throw new ParsingError("Could not load dictionary %s".formatted(dictionaryType.getCanonicalName()));
        }
    }

    public static Dictionary fromLanguageDirectory(
            Class<? extends Dictionary> dictionaryType,
            Locale defaultLocale,
            String languageFilesPath,
            String languageFilesExtension) throws ParsingError
    {
        return fromLanguageDirectory(
                dictionaryType,
                new YamlTranslationParser(),
                defaultLocale,
                languageFilesPath,
                languageFilesExtension);
    }

    public static <R> Dictionary fromLanguageDirectory(
            TranslationParser<R> parser,
            Locale defaultLocale,
            String languageFilesPath,
            String languageFilesExtension) throws ParsingError
    {
        return fromLanguageDirectory(
                SpigotDictionary.class,
                parser,
                defaultLocale,
                languageFilesPath,
                languageFilesExtension);
    }
}
