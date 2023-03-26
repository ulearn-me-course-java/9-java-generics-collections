package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public final class Pair<T,U> {
    private T firstValue;
    private U secondValue;

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

    public static <T, U> Pair<T, U> of(T first, U second){
        return new Pair<T, U>(first, second);
    }

    public void ifPresent(BiConsumer consumer){
        if (this.firstValue != null && this.secondValue != null){
            consumer.accept((T) this.firstValue,(U) this.secondValue);
        }
    }
}
