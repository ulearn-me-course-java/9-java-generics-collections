package com.example.task01;

import java.io.IOException;

public class Task01Main {
    public static void main(String[] args) throws IOException {

        Pair<Integer, String> Pair1 = Pair.of(1, "hello");
        String s = Pair1.getSecond(); // "hello"

        Pair1.ifPresent((first, second) -> {
            System.out.println(first);
            System.out.println(second);
        });

        Pair<Integer, String> Pair2 = Pair.of(1, "hello");
        boolean mustBeTrue = Pair1.equals(Pair2); // true!
        System.out.println(mustBeTrue);
        boolean mustAlsoBeTrue = Pair1.hashCode() == Pair2.hashCode(); // true!
        System.out.println(mustAlsoBeTrue);

    }

}
