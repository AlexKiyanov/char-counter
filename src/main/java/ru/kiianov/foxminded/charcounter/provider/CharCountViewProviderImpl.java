package ru.kiianov.foxminded.charcounter.provider;

import java.util.Map;
import java.util.stream.Collectors;

public class CharCountViewProviderImpl implements CharCountViewProvider {
    private static final String RESULT_TEMPLATE = "\"%s\" - %s";

    @Override
    public String provideView(String text, Map<String, Long> characterToCount) {
        return text +
                System.lineSeparator() +
                appendResultMap(characterToCount) +
                System.lineSeparator();
    }

    private String appendResultMap(Map<String, Long> characterToCount) {
        return characterToCount.entrySet().stream()
                .map(entry -> String.format(RESULT_TEMPLATE, entry.getKey(), entry.getValue()))
                .collect(Collectors.joining(System.lineSeparator()));
    }
}
