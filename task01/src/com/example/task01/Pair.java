package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T, V> {
    private final T firstValue;
    private final V secondValue;

    private Pair(T firstValue, V secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public static <T, V> Pair<T, V> of(T firstValue, V secondValue) {
        return new Pair<>(firstValue, secondValue);
    }

    public T getFirst() {
        return firstValue;
    }

    public V getSecond() {
        return secondValue;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (obj == null || obj.getClass() != getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) obj;
        return firstValue.equals(pair.firstValue) && secondValue.equals(pair.secondValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstValue, secondValue);
    }

    public void ifPresent(BiConsumer<? super T, ? super V> biConsumer) {
        if (firstValue != null && secondValue != null)
            biConsumer.accept(firstValue, secondValue);
    }
}
