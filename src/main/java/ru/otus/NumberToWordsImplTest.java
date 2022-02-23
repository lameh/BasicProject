package ru.otus;

import ru.otus.assertions.Assertions;

public class NumberToWordsImplTest {

    public void testMinConstructingResultString() {
        String scenario = "Тест на минимальное значение";
        try {
            var arrayStr = NumberToWordsImpl.convertStringToArray("1.00");
            var testResult = NumberToWordsImpl.constructingResultString(arrayStr);

            String result = new String("Один рубль 00 копеек");
            Assertions.assertEquals(testResult, result);
            System.out.printf("\"%s\" passed %n", scenario);
        } catch (Throwable e) {
            System.err.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }

    public void testMaxConstructingResultString() {
        String scenario = "Тест на максимальное значение";
        try {
            var arrayStr = NumberToWordsImpl.convertStringToArray("99999999999.99");
            var testResult = NumberToWordsImpl.constructingResultString(arrayStr);

            String result = new String("Девяносто девять миллиардов девятьсот девяносто девять миллионов девятьсот девяносто девять тысяч девятьсот девяносто девять рублей 99 копеек");
            Assertions.assertEquals(testResult, result);
            System.out.printf("\"%s\" passed %n", scenario);
        } catch (Throwable e) {
            System.err.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
        }
    }
}
/*
    public void testNonNumberRunConverter() {
        String scenario = "Тест на нечисловое значение";
        try {
            var arrayStr = NumberToWordsImpl.convertStringToArray("9999999999.99");
            var testResult = NumberToWordsImpl.constructingResultString(arrayStr);

            String result = new String("Девяносто девять миллиардов девятьсот девяносто девять миллионов девятьсот девяносто девять тысяч девятьсот девяносто девять рублей 99 копеек");
            Assertions.assertEquals(testResult, result);
            System.out.printf("\"%s\" passed %n", scenario);
        } catch (Throwable e) {
            System.err.printf("\"%s\" fails with message \"%s\" %n", scenario, e.getMessage());
        }
}
*/