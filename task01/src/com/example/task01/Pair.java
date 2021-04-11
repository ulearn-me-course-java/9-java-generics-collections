package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T, M> {
    private final T first;
    private final M second;

    private Pair(T first, M second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public M getSecond() {
        return second;
    }

    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof Pair))
            return false;
        Pair<?, ?> other = (Pair<?, ?>) obj;
        return Objects.equals(first, other.first)
                && Objects.equals(second, other.second);
    }

    public int hashCode()  {
        return Objects.hash(first, second);
    }

    public static <T, M> Pair<T, M> of(T first, M second) {
        return new Pair<>(first, second);
    }

    public void ifPresent(BiConsumer<? super T, ? super M> biConsumer) {
        if (first != null && second != null)
            biConsumer.accept(first, second);
    }
}
