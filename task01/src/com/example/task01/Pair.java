package com.example.task01;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.BiConsumer;

public final class Pair<T, U> {

    private final T first;

    private final U second;


    private Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }


    public static <T, U> Pair<T, U> of(T first, U second) {
        return new Pair<>(first, second);
    }


    public T getFirst() {
        return first;
    }


    public U getSecond() {
        return second;
    }


    public void ifPresent(BiConsumer<? super T, ? super  U> biConsumer) {
        if (first != null && second != null)
            biConsumer.accept(first, second);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Pair)) {
            return false;
        }

        Pair<?, ?> other = (Pair<?, ?>) obj;
        return Objects.equals(first, other.first) && Objects.equals(second, other.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
