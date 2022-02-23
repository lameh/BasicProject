package ru.otus;

public class Main {

    public static void main(String[] args) {
        IOService ioService = new IOStreamsService(System.out, System.in);
        BasicProject application = new BasicProject(ioService);

        new NumberToWordsImplTest().testMinConstructingResultString();
        new NumberToWordsImplTest().testMaxConstructingResultString();
        new BasicProjectTest().testRunConverter();
        application.runConverter();
    }
}
