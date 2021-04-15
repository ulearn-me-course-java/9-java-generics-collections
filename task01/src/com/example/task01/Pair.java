package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Pair<T1, T2> {

    private T1 firstElement;
    private T2 secondElement;

    private Pair(T1 firstElement, T2 secondElement) {
        this.firstElement = firstElement;
        this.secondElement = secondElement;
    }

    public static <T1, T2> Pair of(T1 first, T2 second) {
        return new Pair(first, second);
    }

    public void ifPresent(BiConsumer<? super T1, ? super T2> consumer) {
        if (firstElement != null && secondElement != null)
            consumer.accept(firstElement, secondElement);
    }

    public T1 getFirst() {
        return firstElement;
    }

    public T2 getSecond() {
        return secondElement;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(firstElement, pair.firstElement) && Objects.equals(secondElement, pair.secondElement);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstElement, secondElement);
    }
}
