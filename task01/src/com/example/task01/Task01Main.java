package com.example.task01;

import javax.swing.text.html.Option;
import java.io.IOException;
import java.io.InputStream;
import java.util.Optional;
import java.util.function.BiConsumer;

public class Task01Main {
    public static void main(String[] args) throws IOException {

        // TODO С корректно реализованным классом Pair должен компилироваться и успешно работать следующий код:


        Pair<Integer, String> pair = Pair.of(1, "hello");
        Integer i = pair.getFirst(); // 1
        String s = pair.getSecond(); // "hello"

        pair.ifPresent((first, second) -> {
            System.out.println(first);
            System.out.println(second);
        });

        BiConsumer<Integer, String> printPairValues = (first, second) ->
                System.out.println("First element: " + first + ", Second element: " + second);
        pair.ifPresent(printPairValues);

        Pair<Integer, String> pair2 = Pair.of(1, "hello");
        boolean mustBeTrue = pair.equals(pair2); // true!
        boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!
        System.out.println(mustBeTrue);
        System.out.println(mustAlsoBeTrue);

    }

}
