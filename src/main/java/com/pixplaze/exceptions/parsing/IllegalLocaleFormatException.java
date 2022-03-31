package com.pixplaze.exceptions.parsing;

public class IllegalLocaleFormatException extends RuntimeException {
    public IllegalLocaleFormatException() {
        super("Illegal locale format in parsing source!");
    }
}
