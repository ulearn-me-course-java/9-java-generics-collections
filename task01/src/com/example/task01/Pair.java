package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T, V> {
    private final T firstValue;
    private final V secondValue;

    private Pair(T v1, V v2) {
        firstValue = v1;
        secondValue = v2;
    }

    public static <T, V> Pair<T, V> of(T v1, V v2) {
        return new Pair<>(v1, v2);
    }

    public void ifPresent(BiConsumer<? super T, ? super V> consumer) {
        if (firstValue != null && secondValue != null)
            consumer.accept(firstValue, secondValue);
    }

    public T getFirst() {
        return firstValue;
    }

    public V getSecond() {
        return secondValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(firstValue, pair.firstValue) && Objects.equals(secondValue, pair.secondValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstValue, secondValue);
    }
}
