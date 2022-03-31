package com.pixplaze.parser;

import com.pixplaze.exceptions.parsing.KeywordsNotSpecifiedException;
import com.pixplaze.exceptions.parsing.LanguageNotSpecifiedException;
import com.pixplaze.exceptions.parsing.TranslationNotSpecifiedException;
import com.pixplaze.keyword.Translation;

import java.io.File;

public interface TranslationParser<T> {
    Translation parse(T parsing) throws
            TranslationNotSpecifiedException,
            LanguageNotSpecifiedException,
            KeywordsNotSpecifiedException;
}
