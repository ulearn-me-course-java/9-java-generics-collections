package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair <T1, T2> {
    private T1 firstValue;
    private T2 secondValue;
    private Pair(T1 firstValue, T2 secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }
    public T1 getFirst() {
        return firstValue;
    }
    public T2 getSecond() {
        return secondValue;
    }
    public void setFirst(T1 firstValue) {
        this.firstValue = firstValue;
    }
    public void setSecond(T2 secondValue) {
        this.secondValue = secondValue;
    }

    public static <T1, T2> Pair<T1, T2> of(T1 first, T2 second){
        return new Pair<T1,  T2>(first, second);
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

    public void ifPresent(BiConsumer consumer){
        if (this.firstValue != null && this.secondValue != null){
            consumer.accept((T1) this.firstValue,(T2) this.secondValue);
        }
    }
}
