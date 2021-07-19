import org.junit.jupiter.api.Test;
import ru.kiianov.foxminded.charcounter.cache.LRUCacheProvider;
import ru.kiianov.foxminded.charcounter.provider.CharCountProviderImpl;
import ru.kiianov.foxminded.charcounter.provider.CharCountViewProviderImpl;
import ru.kiianov.foxminded.charcounter.provider.StaticsProvider;
import ru.kiianov.foxminded.charcounter.validator.StringValidatorImpl;

import static org.junit.jupiter.api.Assertions.assertEquals;

class StaticsProviderITTest {
    final int cacheSize = 10;
    StaticsProvider staticsProvider = new StaticsProvider(new CharCountProviderImpl(),
            new CharCountViewProviderImpl(),
            new StringValidatorImpl(),
            new LRUCacheProvider(cacheSize));

    @Test
    void provideStaticsShouldReturnResult() {
        String inputString = "hello world!";
        String expected = "hello world!\n" +
                "\" \" - 1\n" +
                "\"!\" - 1\n" +
                "\"r\" - 1\n" +
                "\"d\" - 1\n" +
                "\"e\" - 1\n" +
                "\"w\" - 1\n" +
                "\"h\" - 1\n" +
                "\"l\" - 3\n" +
                "\"o\" - 2\n";
        assertEquals(expected, staticsProvider.provideStatics(inputString));
    }
}
