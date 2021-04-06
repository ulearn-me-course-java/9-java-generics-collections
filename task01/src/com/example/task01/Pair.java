package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T, T1> {

    private final T first;
    private final T1 second;

    public static <T, T1> Pair of(T firstValue, T1 secondValue) {
        return new Pair(firstValue, secondValue);
    }

    private Pair(T first, T1 secondValue) {
        this.first = first;
        this.second = secondValue;
    }

    public T getFirst(){
        return first;
    }
    public T1 getSecond(){
        return second;
    }

    public void ifPresent(BiConsumer<? super T, ? super T1> consumer) {
        if (first != null && second!=null)
            consumer.accept(first,second);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

}
