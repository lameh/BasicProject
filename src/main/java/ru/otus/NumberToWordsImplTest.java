package ru.otus;

import ru.otus.assertions.Assertions;

import static ru.otus.NumberToWordsImpl.*;

public class NumberToWordsImplTest {

    public void testConstructingResultString() {
        String scenario = "Тест на корректность объекта в стэке";
        try {

        } catch (Throwable e) {
            System.err.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }
}
