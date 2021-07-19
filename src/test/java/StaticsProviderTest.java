import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.kiianov.foxminded.charcounter.cache.LRUCacheProvider;
import ru.kiianov.foxminded.charcounter.provider.CharCountProvider;
import ru.kiianov.foxminded.charcounter.provider.CharCountViewProvider;
import ru.kiianov.foxminded.charcounter.provider.StaticsProvider;
import ru.kiianov.foxminded.charcounter.validator.StringValidator;

import java.util.Map;
import java.util.TreeMap;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class StaticsProviderTest {

    @Mock
    CharCountProvider countProvider;
    @Mock
    CharCountViewProvider viewProvider;
    @Mock
    StringValidator validator;
    @Mock
    LRUCacheProvider cache;

    @InjectMocks
    StaticsProvider staticsProvider;

    @Test
    void provideStaticsShouldReturnResult() {
        final String inputString = "aaabbcccc";
        final String resultString = "aaabbcccc\n" +
                "\"a\" - 3\n" +
                "\"b\" - 2\n" +
                "\"c\" - 4\n";
        final Map<String, Long> resultMap = new TreeMap<>();
        resultMap.put("a", 3L);
        resultMap.put("b", 2L);
        resultMap.put("c", 4L);

        when(countProvider.provideCount(inputString)).thenReturn(resultMap);
        when(viewProvider.provideView(inputString, resultMap)).thenReturn(resultString);

        assertEquals(resultString, staticsProvider.provideStatics(inputString));
        verify(validator).validate(inputString);
    }

    @Test
    void provideStaticsShouldThrowExceptionForBlankInputString() {
        doThrow(IllegalArgumentException.class).when(validator).validate(" \n");

        assertThrows(IllegalArgumentException.class, () -> staticsProvider.provideStatics(" \n"));
        verifyNoInteractions(viewProvider);
        verifyNoInteractions(countProvider);
    }

    @Test
    void provideStaticsShouldReturnExistingResultIfItContainsIntoCache() {
        final String inputString = "aaabbcccc";
        final Map<String, Long> resultMap = new TreeMap<>();
        resultMap.put("a", 3L);
        resultMap.put("b", 2L);
        resultMap.put("c", 4L);

        when(cache.isPresent(inputString)).thenReturn(true);
        when(cache.getValue(inputString)).thenReturn(resultMap);

        staticsProvider.provideStatics(inputString);
        verify(cache).getValue(inputString);
    }
}
