package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T, V> {
    private final T first;
    private final V second;

    public Pair(T first, V second) {
        this.first = first;
        this.second = second;
    }
    public static <T, V> Pair<T,V> of(T first, V second) {
        return new Pair<>(first, second);
    }

    public T getFirst() {
        return first;
    }
    public V getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }


    public void ifPresent(BiConsumer biConsumer) {
        if (first != null && second != null)
            biConsumer.accept(first, second);
    }
}
