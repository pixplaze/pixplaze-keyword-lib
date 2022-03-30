package com.pixplaze.keyword;

import java.util.Locale;

public interface Translation {
    Locale locale();
    String get(String keyword);
}
