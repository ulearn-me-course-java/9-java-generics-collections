package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public final class Pair<T, K> {

    private final T first;

    private final K second;

    private Pair(T firstValue, K secondValue) {
        this.first = firstValue;
        this.second = secondValue;
    }

    public static <T, K> Pair<T, K> of(T first, K second) {
        return new Pair<>(first, second);
    }

    public T getFirst() {
        return first;
    }

    public K getSecond() {
        return second;
    }

    public void ifPresent(BiConsumer<? super T, ? super K> biConsumer) {
        if (first != null && second != null)
            biConsumer.accept(first, second);
    }


    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Pair)) return false;
        Pair otherObject = (Pair) o;
        return first == otherObject.first &&
                second == otherObject.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
