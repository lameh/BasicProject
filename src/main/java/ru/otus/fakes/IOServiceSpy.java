package ru.otus.fakes;

import ru.otus.IOService;

import java.util.List;

public class IOServiceSpy implements IOService {

    private final List<String> actualFlow;
    private int attempt = 1;

    public IOServiceSpy(List<String> actualFlow) {
        this.actualFlow = actualFlow;
    }

    @Override
    public void outputString(String message) {
        actualFlow.add(message);
    }

    @Override
    public String inputString() {
        if (attempt == 1) {
            attempt++;
            return "p";
        } else if (attempt == 2) {
            attempt++;
            return "-1";
        } else return "exit";
    }
}
