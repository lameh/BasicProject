package ru.otus;

import java.io.*;

public class BasicProject {

    private final IOService ioService;

    public BasicProject(IOService ioService) {
        this.ioService = ioService;
    }

    public void runConverter() {
//        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        ioService.outputString("Введите число в формате xxx.yy или exit");
        while(true) {
//            System.out.println("Введите число в формате xxx.yy или exit");
            try {
                String input = ioService.inputString();
                if (input.equals("exit")) {
                    break;
                } else {
                    double number = Double.parseDouble(input);
                    if (number <= 0) {
                        ioService.outputString("Число не может быть отрицательным!");
                        continue;
                    }
                    if (!input.contains(".")) {
                        input = input.concat(".00");
                    }
                    if (input.contains(",")) {
                        input = input.replace(",", ".");
                    }

                    String result = NumberToWordsImpl.digits2Text(input);

                    ioService.outputString("Число прописью: " + result);
                }
            } catch (Exception e) {
                ioService.outputString("Введите корректное значение!");
            }
        }
    }
}
