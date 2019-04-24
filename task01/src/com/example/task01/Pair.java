package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<F, S> {
    private final F first;
    private final S second;

    private Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(first) * Objects.hashCode(second);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }

        Pair<?, ?> other = (Pair<?, ?>) obj;
        return Objects.equals(first, other.getFirst()) &&
                Objects.equals(second, other.getSecond());
    }

    public static <T, V> Pair<T, V> of(T value1, V value2) {
        return new Pair<>(value1, value2);
    }

    public void ifPresent(BiConsumer<? super F, ? super S> biConsumer){
        if (first != null && second != null){
            biConsumer.accept(first, second);
        }
    }
}
