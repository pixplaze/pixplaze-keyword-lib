package com.pixplaze.exceptions;

import java.nio.file.Path;

public class NoLanguageSpecifiedException extends RuntimeException {
    public NoLanguageSpecifiedException(Path path) {
        super("No language specified in translation file %s".formatted(path));
    }
}
