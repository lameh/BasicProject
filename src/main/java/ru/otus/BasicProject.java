package ru.otus;

import java.io.*;

public class BasicProject {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        System.out.println("Введите число в формате xxx.yy");
        String input = reader.readLine();
        double number = Double.parseDouble(input);

        String result = NumberToWords.digits2Text(Double.valueOf(input));

        System.out.println("Число прописью: " + result);
    }
}
