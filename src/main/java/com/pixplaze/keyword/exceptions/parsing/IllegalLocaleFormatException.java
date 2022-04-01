package com.pixplaze.keyword.exceptions.parsing;

public class IllegalLocaleFormatException extends RuntimeException {
    public IllegalLocaleFormatException() {
        super("Illegal locale format in parsing source!");
    }
}
