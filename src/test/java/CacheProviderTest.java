import org.junit.jupiter.api.Test;
import ru.kiianov.foxminded.charcounter.cache.LRUCacheProvider;

import java.util.LinkedHashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class CacheProviderTest {
    private static final int CACHE_SIZE = 10;
    private final LRUCacheProvider cache = new LRUCacheProvider(CACHE_SIZE);

    @Test
    void cacheShouldSaveCountResult() {
        final String text = "aaabbcccc";
        final Map<String, Long> resultMap = new LinkedHashMap<>();
        resultMap.put("a", 3L);
        resultMap.put("b", 2L);
        resultMap.put("c", 4L);
        cache.save(text, resultMap);
        assertTrue(cache.isPresent(text));
    }

    @Test
    void cacheShouldGetResult() {
        final String text = "aaabbcccc";
        final Map<String, Long> resultMap = new LinkedHashMap<>();
        resultMap.put("a", 3L);
        resultMap.put("b", 2L);
        resultMap.put("c", 4L);
        cache.save(text, resultMap);
        assertEquals(resultMap, cache.getValue(text));
    }

    @Test
    void cacheShouldRemoveEldestValue() {
        final Map<String, Long> resultMap = new LinkedHashMap<>();
        resultMap.put("s", 1L);
        for (int i = 0; i < CACHE_SIZE + 1; i++) {
            cache.save(Integer.toString(i), resultMap);
        }

        assertFalse(cache.isPresent("0"));
    }
}
