package com.pixplaze.keyword.exceptions;

public class EmptyDictionaryError extends Error {
    public EmptyDictionaryError(String message) {
        super(message);
    }

    public EmptyDictionaryError() {
        this("Failed to load localization: empty dictionary.");
    }
}
