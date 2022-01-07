package ru.kiianov.foxminded.charcounter;

import ru.kiianov.foxminded.charcounter.cache.LRUCacheProvider;
import ru.kiianov.foxminded.charcounter.provider.CharCountProviderImpl;
import ru.kiianov.foxminded.charcounter.provider.CharCountViewProviderImpl;
import ru.kiianov.foxminded.charcounter.provider.StaticsProvider;
import ru.kiianov.foxminded.charcounter.validator.StringValidatorImpl;

import java.util.Scanner;

public class CharacterCountingApplication {
    public static void main(String[] args) {
        final int CACHE_SIZE = 10;
        final StaticsProvider staticsProvider = new StaticsProvider(new CharCountProviderImpl(),
                new CharCountViewProviderImpl(),
                new StringValidatorImpl(),
                new LRUCacheProvider(CACHE_SIZE));

        try (Scanner scanner = new Scanner(System.in)) {
            System.out.println("Waiting your string: ");
            String inputString = scanner.nextLine();
            while (!"exit".equalsIgnoreCase(inputString)) {
                System.out.println(staticsProvider.provideStatics(inputString));
                System.out.println("\nPlease enter next string or \"exit\" for exit of app");
                inputString = scanner.nextLine();
            }
        }
    }
}
