package com.pixplaze.exceptions.parsing;

public class KeywordsNotSpecifiedException extends ParsingException {
    public KeywordsNotSpecifiedException() {
        super("Keyword section is not specified!");
    }
}
