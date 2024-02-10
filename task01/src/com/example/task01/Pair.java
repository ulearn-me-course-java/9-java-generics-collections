package com.example.task01;


import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<A, B> {
    private final A first;
    private final B second;

    private Pair(A first, B second) {
        this.first = first;
        this.second = second;
    }

    public static <A, B> Pair<A, B> of(A first, B second) {
        return new Pair<>(first, second);
    }

    public A getFirst() {
        return first;
    }

    public B getSecond() {
        return second;
    }

    public void ifPresent(BiConsumer consumer) {
        if (first != null && second != null){
            consumer.accept(first, second);
        }
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        Pair<A, B> pair = (Pair<A, B>) obj;

        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }
}