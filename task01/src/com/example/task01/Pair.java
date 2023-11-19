package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<F, S> {
    public F first;
    public S second;

    private Pair(F firstValue, S secondValue) {
        first = firstValue;
        second = secondValue;
    }

    public static <F, S> Pair<F, S> of(F firstValue, S secondValue) {
        return new Pair<>(firstValue, secondValue);
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Pair<F, S> pair = (Pair<F, S>) obj;
        return this.first.equals(pair.first) && this.second.equals(pair.second);
    }

    public int hashCode() {
        return Objects.hash(this.first, this.second);
    }

    public void ifPresent(BiConsumer consumer) {
        if (this.first != null && this.second != null) {
            consumer.accept(this.first, this.second);
        }
    }
}
