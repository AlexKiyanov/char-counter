package ru.kiianov.foxminded.charcounter.provider;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CharCountProviderImpl implements CharCountProvider {

    @Override
    public Map<String, Long> provideCount(String text) {

        return new LinkedHashMap<>(text
                .codePoints()
                .mapToObj(Character::toString)
                .collect(Collectors.groupingBy(Function.identity(), Collectors.counting())));
    }
}
