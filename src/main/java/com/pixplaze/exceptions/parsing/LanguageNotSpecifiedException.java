package com.pixplaze.exceptions.parsing;

public class LanguageNotSpecifiedException extends ParsingException {
    public LanguageNotSpecifiedException() {
        super("Language is not defined in parsing source!");
    }
}
