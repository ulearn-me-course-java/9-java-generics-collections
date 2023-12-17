package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T, U> {

    private final T firstValue;
    private final U secondValue;

    public static <V, N> Pair<V, N> of(V firstValue, N secondValue) {
        return new Pair<>(firstValue, secondValue);
    }

    private Pair(T firstValue, U secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public T getFirst() {
        return firstValue;
    }

    public U getSecond() {
        return secondValue;
    }

    @Override
    public boolean equals(Object object) {
        if (getClass() != object.getClass()) {
            return false;
        }
        Pair<?, ?> pair = (Pair<?, ?>) object;
        return pair.firstValue.equals(firstValue) && pair.secondValue.equals(secondValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstValue, secondValue);
    }

    public void ifPresent(BiConsumer<? super T, ? super U> biConsumer) {
        if (firstValue != null && secondValue != null) {
            biConsumer.accept(firstValue, secondValue);
        }
    }
}
