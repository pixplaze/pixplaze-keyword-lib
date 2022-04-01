package com.pixplaze.keyword.parser;

import com.google.common.base.Charsets;
import com.google.common.io.Files;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.pixplaze.keyword.DefaultTranslation;
import com.pixplaze.keyword.Translation;
import com.pixplaze.keyword.exceptions.parsing.*;

import java.io.File;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class JsonTranslationParser implements TranslationParser<JsonObject> {

    @Override
    public Translation parse(JsonObject parsing) throws
            TranslationNotSpecifiedException,
            LocaleNotSpecifiedException,
            KeywordsNotSpecifiedException,
            IllegalLocaleFormatException
    {
        var translation = parsing.getAsJsonObject("translation");
        if (translation == null) throw new TranslationNotSpecifiedException();

        var language = translation.get("locale").getAsString();
        if (language == null) throw new LocaleNotSpecifiedException();

        var locale = Locale.forLanguageTag(language);
        if (locale.toLanguageTag().equals("und") || locale.getCountry().isEmpty()) throw new IllegalLocaleFormatException();

        var keywords = translation.getAsJsonObject("keywords");
        if (keywords == null) throw new KeywordsNotSpecifiedException();

        var lang = keywords.entrySet().stream().collect(
                Collectors.toMap(Map.Entry::getKey, e -> e.getValue().getAsString())
        );
        return new DefaultTranslation(locale, lang);
    }

    @Override
    public <T> JsonObject load(T file) {
        try {
            return JsonParser.parseString(Files.asCharSource((File) file, Charsets.UTF_8).read()).getAsJsonObject();
        } catch (Exception e) {
            throw new ParsingError();
        }
    }


}
