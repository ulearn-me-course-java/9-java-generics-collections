package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T, U> {

    private final T firstValue;
    private final U secondValue;

    public static <T, U> Pair<T, U> of(T firstValue, U secondValue) {
        return new Pair<T, U>(firstValue, secondValue);
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

    public boolean equals(Pair<T, U> pair) {
        return firstValue == pair.getFirst() && secondValue == pair.getSecond();
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
