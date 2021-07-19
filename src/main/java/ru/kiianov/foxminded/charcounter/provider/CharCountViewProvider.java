package ru.kiianov.foxminded.charcounter.provider;

import java.util.Map;

public interface CharCountViewProvider {
    String provideView(String text, Map<String, Long> characterToCount);
}
