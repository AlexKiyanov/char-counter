package ru.kiianov.foxminded.charcounter.provider;

import ru.kiianov.foxminded.charcounter.cache.LRUCacheProvider;
import ru.kiianov.foxminded.charcounter.validator.StringValidator;

import java.util.Map;

public class StaticsProvider {
    private final CharCountProvider charCountProvider;
    private final CharCountViewProvider viewProvider;
    private final StringValidator validator;
    private final LRUCacheProvider cache;

    public StaticsProvider(CharCountProvider charCountProvider,
                           CharCountViewProvider viewProvider,
                           StringValidator validator, LRUCacheProvider cache) {
        this.charCountProvider = charCountProvider;
        this.viewProvider = viewProvider;
        this.validator = validator;
        this.cache = cache;
    }

    public String provideStatics(String text) {
        validator.validate(text);

        final Map<String, Long> characterToCount;

        if (cache.isPresent(text)) {
            characterToCount = cache.getValue(text);
        } else {
            characterToCount = charCountProvider.provideCount(text);
            cache.save(text, characterToCount);
        }

        return viewProvider.provideView(text, characterToCount);

    }
}
