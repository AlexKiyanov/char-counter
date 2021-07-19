import org.junit.jupiter.api.Test;
import ru.kiianov.foxminded.charcounter.provider.CharCountViewProvider;
import ru.kiianov.foxminded.charcounter.provider.CharCountViewProviderImpl;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CharCountViewProviderTest {
    private final CharCountViewProvider viewProvider = new CharCountViewProviderImpl();

    @Test
    void provideViewShouldReturnCorrectView() {
        String expected = "hello world!\n" +
                "\" \" - 1\n" +
                "\"!\" - 1\n" +
                "\"d\" - 1\n" +
                "\"e\" - 1\n" +
                "\"h\" - 1\n" +
                "\"l\" - 3\n" +
                "\"o\" - 2\n" +
                "\"r\" - 1\n" +
                "\"w\" - 1\n";

        final Map<String, Long> resultMap = new TreeMap<>();

        resultMap.put(" ", 1L);
        resultMap.put("!", 1L);
        resultMap.put("r", 1L);
        resultMap.put("d", 1L);
        resultMap.put("e", 1L);
        resultMap.put("w", 1L);
        resultMap.put("h", 1L);
        resultMap.put("l", 3L);
        resultMap.put("o", 2L);

        assertEquals(expected, viewProvider.provideView("hello world!", resultMap));
    }
}
