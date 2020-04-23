package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<F, S> {
    private final F firstValue;
    private final S secondValue;

    private Pair(F firstValue, S secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public F getFirst() {
        return firstValue;
    }

    public S getSecond() {
        return secondValue;
    }

    public boolean equals(Pair<F, S> pair) {
        return (this.firstValue == pair.getFirst() && this.secondValue == pair.getSecond());
    }

    public int hashCode() {
        return 31 * Objects.hashCode(firstValue) + Objects.hashCode(secondValue);
    }

    public static <F, S> Pair<F, S> of(F firstValue, S secondValue) {
        return new Pair<>(firstValue, secondValue);
    }

    public void ifPresent(BiConsumer<? super F, ? super S> biConsumer) {
        if (firstValue != null && secondValue != null)
            biConsumer.accept(firstValue, secondValue);
    }
}
