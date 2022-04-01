package com.pixplaze.keyword.exceptions;

public class DictionaryError extends Error {
    public DictionaryError() {
        this("Unable to create translation dictionary!");
    }

    public DictionaryError(String message) {
        super(message);
    }
}
