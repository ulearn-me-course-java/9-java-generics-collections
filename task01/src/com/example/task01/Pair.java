package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T1, T2> {

    private final T1 first;
    private final T2 second;

    private Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    public static <T1, T2> Pair of(T1 first, T2 second) {
        return new Pair<>(first, second);
    }

    // "? super T" - это некое выражение маска
    // Если планируем получать что-то, то используем extends, если отдавать, то super

    // Принимает объект любого super типа T, если extends, то любого от типа T
    public void ifPresent(BiConsumer<? super T1, ? super T2> biConsumer) {
        if (first != null && second != null) {
            biConsumer.accept(first, second);
        }
    }

    public boolean equals(Pair<T1, T2> other) {
        return other.getFirst() == first && other.getSecond() == second;
    }

    public int hashCode() {
        return Objects.hashCode(first) + Objects.hashCode(second);
    }
}