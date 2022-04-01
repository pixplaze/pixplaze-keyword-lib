package com.pixplaze.exceptions;

import com.pixplaze.exceptions.parsing.ParsingError;

public class EmptyDictionaryError extends Error {
    public EmptyDictionaryError(String message) {
        super(message);
    }

    public EmptyDictionaryError() {
        this("Failed to load localization: empty dictionary.");
    }
}
