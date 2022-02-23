package ru.otus;

import java.io.IOException;

public interface IOService {
    void outputString(String message);
    String inputString() throws IOException;
}
