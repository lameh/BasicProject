package ru.otus;

import java.io.*;

public class IOStreamsService implements IOService {

    private final PrintStream out;
    private final BufferedReader reader;

    public IOStreamsService(PrintStream out, InputStream in) {
        this.out = out;
        this.reader = new BufferedReader(new InputStreamReader(in));
    }

    @Override
    public void outputString(String message) {
        out.println(message);
    }

    @Override
    public String inputString() throws IOException {
        return reader.readLine();
    }
}
