package com.example.task01;

import java.util.function.BiConsumer;

public class Pair<T1, T2> {
    private final T1 firstValue;
    private final T2 secondValue;

    private Pair(T1 firstValue, T2 secondValue){
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public static <T1, T2> Pair<T1, T2> of(T1 firstValue, T2 secondValue){
        return new Pair<>(firstValue, secondValue);
    }

    public T1 getFirst() {
        return firstValue;
    }

    public T2 getSecond() {
        return secondValue;
    }

    public void ifPresent(BiConsumer consumer){
        if (this.firstValue != null && this.secondValue != null) {
            consumer.accept((T1) this.firstValue, (T2) this.secondValue);
        }
    }

    @Override
    public int hashCode(){
        return firstValue.hashCode() + firstValue.hashCode();
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) obj;
        return this.firstValue.equals(pair.firstValue) && this.secondValue.equals(pair.secondValue);
    }
}
