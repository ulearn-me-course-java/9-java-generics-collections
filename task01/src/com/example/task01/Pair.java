package com.example.task01;

import java.util.function.BiConsumer;
import java.util.Objects;

public class Pair<T,U> {
    // TODO напишите реализацию
    private final T first;
    private final U second;

    private Pair(T first, U second) {
        this.first = first;
        this.second = second;
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
        final int prime = 31;
        int result = 1;
        result = prime * result + Objects.hashCode(first);
        result = prime * result + Objects.hashCode(second);
        return result;
    }

    public static <T,U> Pair<T,U> of(T first, U second) {
        return new Pair<>(first, second);
    }

    public void ifPresent(BiConsumer<? super T, ? super U> biConsumer) {
        if (first != null && second != null) {
            biConsumer.accept(first, second);
        }
    }
}
