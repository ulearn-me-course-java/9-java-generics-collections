package com.example.task01;
import java.util.function.BiConsumer;

public class Pair<T, S> {
    private final T firstValue;
    private final S secondValue;

    private Pair(T valueFirst, S valueSecond) {
        this.firstValue = valueFirst;
        this.secondValue = valueSecond;
    }

    public T getFirst() {
        return firstValue;
    }

    public S getSecond() {
        return secondValue;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pair)) return false;
        Pair pair = (Pair) o;
        return firstValue.equals(pair.firstValue) && secondValue.equals(pair.secondValue);
    }

    @Override
    public int hashCode() {
        return firstValue.hashCode() * 3 + secondValue.hashCode() * 7;
    }

    public static <T, S> Pair<T, S> of(T first, S second) {
        return new Pair<>(first, second);
    }

    public void ifPresent(BiConsumer<? super T, ? super S> biConsumer) {
        if(firstValue != null && secondValue != null) {
            biConsumer.accept(firstValue, secondValue);
        }
    }
}