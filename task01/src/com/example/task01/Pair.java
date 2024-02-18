package com.example.task01;

import java.util.HashSet;
import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<F, S> {
    private final F first;
    private final S second;
    private Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }
    public static <F, S> Pair<F, S> of(F first, S second) {
        return new Pair<>(first, second);
    }
    public F getFirst() {
        return first;
    }
    public S getSecond() {
        return second;
    }
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null || getClass() != other.getClass()) {
            return false;
        }
        Pair<?, ?> otherPair = (Pair<?, ?>) other;
        //return first.equals(otherPair.getFirst()) && second.equals(otherPair.getSecond());
        return Objects.equals(first, otherPair.getFirst()) && Objects.equals(second, otherPair.getSecond());
    }
    public void ifPresent(BiConsumer<? super F, ? super S> consumer) {
        if (first != null && second != null) {
            consumer.accept(first, second);
        }
    }
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
