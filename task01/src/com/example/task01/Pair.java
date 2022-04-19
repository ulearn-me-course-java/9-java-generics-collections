package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Pair<T, V> {

    private T firstValue;
    private V secondValue;

    private Pair(T first, V second){
        firstValue = first;
        secondValue = second;
    }

    public static <T, V> Pair<T, V> of(T first, V second){ return new Pair<>(first, second);}

    public T getFirst() {
        return firstValue;
    }

    public V getSecond() {
        return secondValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return firstValue.equals(pair.firstValue) && secondValue.equals(pair.secondValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstValue, secondValue);
    }

    public void ifPresent(BiConsumer<? super T, ? super V> consumer) {
        if (firstValue != null && secondValue != null)
            consumer.accept(firstValue, secondValue);
    }
}
