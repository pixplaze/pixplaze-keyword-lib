package com.pixplaze.keyword;

import com.google.common.reflect.Reflection;
import com.pixplaze.exceptions.parsing.ParsingError;
import com.pixplaze.parser.TranslationLoader;
import com.pixplaze.parser.YamlTranslationLoader;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.HashSet;
import java.util.Set;

public class PixplazeKeywordLib extends JavaPlugin {
    public static void main(String[] args) {
        PixplazeKeywordLib.fromLanguageDirectory(new File(""));
    }


    public static Dictionary fromLanguageDirectory(File dir) throws ParsingError {
        return fromLanguageDirectory(dir, "");
    }

    public static Dictionary fromLanguageDirectory(File dir, String fileExtension) throws ParsingError {
        return fromLanguageDirectory(DefaultDictionary.class, new YamlTranslationLoader(), dir, fileExtension);
    }

    public static <R> Dictionary fromLanguageDirectory(Class<? extends Dictionary> dictionaryType, TranslationLoader<R> parser, File dir, String fileExtension) throws ParsingError {
        var set = new HashSet<Translation>();
        if (dir != null && dir.isDirectory()) {
            var files = dir.listFiles(file -> file.getName().endsWith(fileExtension));

            assert files != null;
            for (var file : files) {
                try {
                    var parsingInstance = parser.load(file);
                    set.add(parser.parse(parsingInstance));
                } catch (Exception e) {
                    throw new ParsingError();
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
