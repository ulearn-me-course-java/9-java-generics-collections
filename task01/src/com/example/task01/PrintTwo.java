package com.example.task01;

import java.util.function.BiConsumer;

public class PrintTwo<F,S> implements BiConsumer<F,S> {
    @Override
    public void accept(F o, S o2) {
        System.out.println(o);
        System.out.println(o2);
    }
}
