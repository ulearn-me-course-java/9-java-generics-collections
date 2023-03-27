package com.example.task01;

import java.util.function.BiConsumer;

public class Pair<T, P> {

    private T firstElem;

    private P secondElem;

    private Pair(T valueFirst, P valueSecond) {
        this.firstElem = valueFirst;
        this.secondElem = valueSecond;
    }

    public static <T, P> Pair<T, P> of(T firstValue, P secondValue) {
        return new Pair<>(firstValue, secondValue);
    }

    public T getFirst() {
        return firstElem;
    }

    public P getSecond() {
        return secondElem;
    }

    @Override
    public int hashCode() {
        return firstElem.hashCode() * 10000 + secondElem.hashCode() / 10000;
    }

    public void ifPresent(BiConsumer consumer) {
        if (this.firstElem != null && this.secondElem != null) {
            consumer.accept((T) this.firstElem, (P) this.secondElem);
        }
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) object;
        return this.firstElem.equals(pair.firstElem) && this.secondElem.equals(pair.secondElem);
    }
}