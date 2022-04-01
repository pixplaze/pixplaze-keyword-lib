package com.pixplaze.keyword.exceptions.parsing;

public class LocaleNotSpecifiedException extends ParsingException {
    public LocaleNotSpecifiedException() {
        super("Locale is not specified in parsing source!");
    }
}
