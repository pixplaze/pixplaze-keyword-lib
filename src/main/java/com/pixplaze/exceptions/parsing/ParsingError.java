package com.pixplaze.exceptions.parsing;

public class ParsingError extends Exception {
    public ParsingError(String message) {
        super(message);
    }

    public ParsingError() {
        this("Unable to create translation dictionary!");
    }
}
