package com.pixplaze.keyword.exceptions.parsing;

public class ParsingError extends Error {

    public ParsingError() {
        this("Could not parse translation sources!");
    }

    public ParsingError(String message) {
        this(message, null);
    }

    public ParsingError(String message, Throwable cause) {
        super(message, cause);
    }
}
