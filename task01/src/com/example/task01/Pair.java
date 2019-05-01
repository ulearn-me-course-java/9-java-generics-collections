package com.example.task01;

import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.BiConsumer;

public class Pair<T, G> {
    private T first;
    private G second;

    private Pair(T first, G second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public G getSecond() {
        return second;
    }

    public boolean equals(Pair pair) {
        return this.first == pair.first && this.second == pair.second;
    }

    public int hashCode() {
        return first.hashCode() + second.hashCode();
    }

    public void ifPresent(BiConsumer<? super T, ? super G> biConsumer) {
        if (first != null && second != null)
            biConsumer.accept(first, second);
    }

    public static <T, G> Pair<T, G> of(T first, G second) {
        return new Pair<>(first, second);
    }
}
