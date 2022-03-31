package com.pixplaze.parser;

import com.pixplaze.exceptions.parsing.KeywordsNotSpecifiedException;
import com.pixplaze.exceptions.parsing.LocaleNotSpecifiedException;
import com.pixplaze.exceptions.parsing.TranslationNotSpecifiedException;
import com.pixplaze.keyword.Translation;

public interface TranslationParser<T> {
    Translation parse(T parsing) throws
            TranslationNotSpecifiedException,
            LocaleNotSpecifiedException,
            KeywordsNotSpecifiedException;
}
