package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair <Tf, Ts> {
    private Tf firstValue;
    private Ts secondValue;

    public Tf getFirst() {
        return firstValue;
    }

    public void setFirst(Tf firstValue) {
        this.firstValue = firstValue;
    }

    public Ts getSecond() {
        return secondValue;
    }

    public void setSecond(Ts secondValue) {
        this.secondValue = secondValue;
    }

    public static <Tf, Ts> Pair<Tf, Ts> of(Tf first, Ts second){
        return new Pair<Tf,  Ts>(first, second);
    }

    private Pair(Tf firstValue, Ts secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(firstValue, pair.firstValue) && Objects.equals(secondValue, pair.secondValue);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstValue, secondValue);
    }

    public void ifPresent(BiConsumer consumer){
        if (this.firstValue != null && this.secondValue != null){
            consumer.accept((Tf) this.firstValue,(Ts) this.secondValue);
        }
    }
}
