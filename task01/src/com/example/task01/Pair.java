package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<A, B> {
    private final A firstValue;
    private final B secondValue;
    
    private Pair(A firstValue, B secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }
    
    public static <A, B> Pair<A, B> of(A firstValue, B secondValue) {
        return new Pair<>(firstValue, secondValue);
    }
    
    public A getFirst() {
        return firstValue;
    }
    
    public B getSecond() {
        return secondValue;
    }
    
    public void ifPresent(BiConsumer<? super A, ? super B> consumer) {
        if (firstValue != null && secondValue != null) {
            consumer.accept(firstValue, secondValue);
        }
    }
    
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(firstValue, pair.firstValue) && Objects.equals(secondValue, pair.secondValue);
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(firstValue, secondValue);
    }
}
