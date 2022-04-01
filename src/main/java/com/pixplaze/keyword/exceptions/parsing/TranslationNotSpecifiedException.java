package com.pixplaze.keyword.exceptions.parsing;

public class TranslationNotSpecifiedException extends ParsingException {
    public TranslationNotSpecifiedException() {
        super("Translation section is not specified in parsing source!");
    }
}
