import org.junit.jupiter.api.Test;
import ru.kiianov.foxminded.charcounter.validator.StringValidator;
import ru.kiianov.foxminded.charcounter.validator.StringValidatorImpl;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StringValidatorTest {
    private final StringValidator validator = new StringValidatorImpl();

    @Test
    void validateShouldThrowExceptionIfInputStringIsBlank() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                validator.validate(""));
        assertEquals("Input String can't be empty", exception.getMessage());
    }

    @Test
    void validateShouldThrowExceptionIfInputIsNull() {
        Throwable exception = assertThrows(IllegalArgumentException.class, () ->
                validator.validate(null));
        assertEquals("Input is null", exception.getMessage());
    }

    @Test
    void validateShouldNotThrowExceptionForCorrectString() {
        assertDoesNotThrow(() -> validator.validate("rrfq43fr"));
    }
}
