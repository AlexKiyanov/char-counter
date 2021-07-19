package ru.kiianov.foxminded.charcounter.provider;

import java.util.Map;

public interface CharCountProvider {
    Map<String, Long> provideCount(String text);
}
