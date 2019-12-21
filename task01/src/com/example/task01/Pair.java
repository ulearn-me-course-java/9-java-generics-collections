package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T, U> {
    private final T first;
    private final U second;

    private Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public static <T, U> Pair<T, U> of(T first, U second) {
        return new Pair<>(first, second);
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public boolean equals(Pair pair) {
        return this.first == pair.first && this.second == pair.second;
    }

    public int hashCode() {
        return Objects.hashCode(first) % 32768 * 37 + Objects.hashCode(second) % 32768;
    }

    public void ifPresent(BiConsumer<? super T, ? super U> biConsumer) {
        if (first != null && second != null) {
            biConsumer.accept(first, second);
        }
    }
}
