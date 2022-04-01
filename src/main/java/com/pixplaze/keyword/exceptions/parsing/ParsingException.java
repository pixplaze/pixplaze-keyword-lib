package com.pixplaze.keyword.exceptions.parsing;

import java.io.File;

public class ParsingException extends RuntimeException {
    public ParsingException(String message) {
        super(message);
    }

    public ParsingException(File file) {
        this("Unable to parse file %s".formatted(file.getAbsolutePath()));
    }
}
