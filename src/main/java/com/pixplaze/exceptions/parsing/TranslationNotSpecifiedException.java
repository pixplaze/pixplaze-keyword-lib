package com.pixplaze.exceptions.parsing;

public class TranslationNotSpecifiedException extends ParsingException {
    public TranslationNotSpecifiedException() {
        super("Translation section is not defined!");
    }
}
