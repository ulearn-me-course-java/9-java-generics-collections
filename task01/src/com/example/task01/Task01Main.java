package com.example.task01;

import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;

public class Task01Main {
    public static void main(String[] args) throws IOException {

        // TODO С корректно реализованным классом Pair должен компилироваться и успешно работать следующий код:


        Pair<Integer, String> pair = Pair.of(1, "hello");
        Pair<Number, String> pair1 = Pair.of(1, "hello");
        System.out.println(pair1.equals(pair));
    }

}
