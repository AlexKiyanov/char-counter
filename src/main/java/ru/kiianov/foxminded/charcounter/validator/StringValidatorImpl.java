package ru.kiianov.foxminded.charcounter.validator;

import static java.util.Objects.isNull;

public class StringValidatorImpl implements StringValidator {
    @Override
    public void validate(String input) {
        if (isNull(input)) {
            throw new IllegalArgumentException("Input is null");
        }
        if (input.isEmpty()) {
            throw new IllegalArgumentException("Input String can't be empty");
        }
    }
}
