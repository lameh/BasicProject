package ru.otus;

public class BasicProject {

    private final IOService ioService;

    public BasicProject(IOService ioService) {
        this.ioService = ioService;
    }

    public void runConverter() {
        ioService.outputString("Введите число в формате xxx.yy или exit");
        while(true) {
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
