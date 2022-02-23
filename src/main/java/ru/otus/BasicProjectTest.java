package ru.otus;

import ru.otus.assertions.Assertions;
import ru.otus.fakes.IOServiceSpy;

import java.util.ArrayList;
import java.util.List;

public class BasicProjectTest {

    public void testRunConverter() {
        String scenario = "Тест на нечисловое и отрицательное значения";
        try {
            List<String> expectedList = List.of("Введите число в формате xxx.yy или exit",
                    "Введите корректное значение!",
                    "Число не может быть отрицательным!");
            List<String> actualList = new ArrayList<>();

            IOService ioServiceSpy = new IOServiceSpy(actualList);
            BasicProject application = new BasicProject(ioServiceSpy);
            application.runConverter();

            Assertions.assertEquals(expectedList.size(), actualList.size());
            for (int i = 0; i < expectedList.size(); i++) {
                Assertions.assertEquals(expectedList.get(i), actualList.get(i));
            }
            System.out.printf("\"%s\" passed %n", scenario);
        } catch (Throwable e) {
            System.err.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }
}
