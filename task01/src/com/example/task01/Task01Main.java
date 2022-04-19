package com.example.task01;

import java.io.IOException;

public class Task01Main {
    public static void main(String[] args) throws IOException {
        Pair<Integer, String> pair = Pair.of(1, "hello");
        Integer i = pair.getkValue(); // 1
        String s = pair.gettValue(); // "hello"

        pair.ifPresent((first, second) -> {
            System.out.println(first);
            System.out.println(second);
        });

        Pair<Integer, String> pair2 = Pair.of(1, "hello");
        boolean mustBeTrue = pair.equals(pair2); // true!
        boolean mustAlsoBeTrue = pair.hashCode() == pair2.hashCode(); // true!

        System.out.println(mustBeTrue);
        System.out.println(mustAlsoBeTrue);

    }

}
