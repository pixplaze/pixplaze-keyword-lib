package com.pixplaze.keyword.exceptions.parsing;

public class KeywordsNotSpecifiedException extends ParsingException {
    public KeywordsNotSpecifiedException() {
        super("Keyword section is not specified in parsing source!");
    }
}
