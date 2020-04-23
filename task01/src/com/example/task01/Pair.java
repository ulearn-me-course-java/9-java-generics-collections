package com.example.task01;

import java.util.function.BiConsumer;

public class Pair<F, S> {
    private F first;
    private S second;

    private Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond(){
        return second;
    }

    public boolean equals(com.example.task01.Pair<F, S> pair) {
        return first.equals(pair.getFirst()) && second.equals(pair.getSecond());
    }

    public int hashCode(){
        return first.hashCode() + second.hashCode()*103;
    }

    public static <T, U> com.example.task01.Pair<T, U> of(T first, U second){
        return new com.example.task01.Pair(first, second);
    }

    public void ifPresent(BiConsumer<? super F, ? super S> biConsumer){
        if (first != null && second != null)
            biConsumer.accept(first, second);
    }
}
