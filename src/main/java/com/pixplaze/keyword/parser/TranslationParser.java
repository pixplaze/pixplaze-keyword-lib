package com.pixplaze.keyword.parser;

import com.pixplaze.keyword.exceptions.parsing.IllegalLocaleFormatException;
import com.pixplaze.keyword.exceptions.parsing.KeywordsNotSpecifiedException;
import com.pixplaze.keyword.exceptions.parsing.LocaleNotSpecifiedException;
import com.pixplaze.keyword.exceptions.parsing.TranslationNotSpecifiedException;
import com.pixplaze.keyword.Translation;

public interface TranslationParser<R> {
    Translation parse(R parsing) throws
            TranslationNotSpecifiedException,
            LocaleNotSpecifiedException,
            KeywordsNotSpecifiedException,
            IllegalLocaleFormatException;

    <T> R load(T source);
}
