package com.pixplaze.keyword.exceptions.parsing;

public class ParsingError extends Error {
    public ParsingError(String message) {
        super(message);
    }

    public ParsingError() {
        this("Unable to create translation dictionary!");
    }
}
