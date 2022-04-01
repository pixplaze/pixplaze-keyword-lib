package com.pixplaze.parser;

import com.pixplaze.exceptions.parsing.IllegalLocaleFormatException;
import com.pixplaze.exceptions.parsing.KeywordsNotSpecifiedException;
import com.pixplaze.exceptions.parsing.LocaleNotSpecifiedException;
import com.pixplaze.exceptions.parsing.TranslationNotSpecifiedException;
import com.pixplaze.keyword.Translation;

public interface TranslationLoader<R> {
    Translation parse(R parsing) throws
            TranslationNotSpecifiedException,
            LocaleNotSpecifiedException,
            KeywordsNotSpecifiedException,
            IllegalLocaleFormatException;

    <T> R load(T source);
}
