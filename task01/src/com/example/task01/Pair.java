package com.example.task01;

import java.util.Objects;

public class Pair<T1, T2> {
    private final T1 firstValue;
    private final T2 secondValue;

    private Pair(T1 firstValue, T2 secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public static <T1, T2> Pair<T1, T2> of(T1 firstValue, T2 secondValue) {
        return new Pair(firstValue, secondValue);
    }

    public T1 getFirst() {
        return firstValue;
    }

    public T2 getSecond() {
        return secondValue;
    }

    public void ifPresent(java.util.function.BiConsumer consumer) {
        if (firstValue != null && secondValue != null)
            consumer.accept(firstValue, secondValue);
    }

    @Override
    public boolean equals(Object other) {
        if (this == other)
            return true;
        if (other == null || getClass().isInstance(other) == false)
            return false;
        Pair otherAsPair = (Pair)other;
        return firstValue.equals((otherAsPair).getFirst()) &&
               secondValue.equals((otherAsPair).getSecond());
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstValue, secondValue);
    }
}
