package com.pixplaze.exceptions.parsing;

public class LocaleNotSpecifiedException extends ParsingException {
    public LocaleNotSpecifiedException() {
        super("Locale is not defined in parsing source!");
    }
}
