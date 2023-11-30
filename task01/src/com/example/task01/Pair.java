package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T1, T2> {
    private final T1 firstValue;
    private final T2 secondValue;

    private Pair(T1 firstValue, T2 secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public static <T1, T2> Pair<T1, T2> of (T1 firstValue, T2 secondValue) {
        return new Pair<>(firstValue, secondValue);
    }

    public T1 getFirst() {
        return firstValue;
    }

    public T2 getSecond() {
        return secondValue;
    }

    public void ifPresent(BiConsumer biConsumer) {
        if (this.firstValue != null && this.secondValue != null) biConsumer.accept(this.firstValue, this.secondValue);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Pair<T1, T2> pair = (Pair<T1, T2>) o;
        return this.firstValue.equals(pair.firstValue) && this.secondValue.equals(pair.secondValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.firstValue, this.secondValue);
    }
}
