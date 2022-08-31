package com.example.task01;

import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;
import java.util.function.Predicate;

public final class Pair<T,G>{
    private final T value1;
    private final G value2;

    private Pair(T value1, G value2) {
        this.value1 = value1;
        this.value2 = value2;
    }
    public static <T,G> Pair<T,G> of(T value1, G value2) {
        return new Pair<>(value1, value2);
    }

    public void ifPresent(BiConsumer<? super T, ? super G> consumer) {
        if (value1 != null && value2 != null) {
            consumer.accept(value1, value2);
        }
    }
    public T getFirst() {
        return value1;
    }
    public G getSecond() {
        return value2;
    }
    @Override
    public boolean equals(Object anObject) {
        if (this == anObject) {
            return true;
        }
        try {
            Pair<T,G> pair = (Pair<T,G>)anObject;
            return value1.equals(pair.value1)&&value2.equals(pair.value2);
        } catch (ClassCastException e) {
            return false;
        }
    }
    @Override
    public int hashCode() {
        return value1.hashCode() + value2.hashCode();
    }
}
