package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<K, T> {
    private final K kValue;
    private final T tValue;

    private Pair(K kValue, T tValue) {
        this.kValue = kValue;
        this.tValue = tValue;
    }

    public K getkValue() {
        return kValue;
    }

    public T gettValue() {
        return tValue;
    }

    public boolean equals(Pair<K, T> pair) {
        return kValue.equals(pair.getkValue()) && tValue.equals(pair.gettValue());
    }

    public int hashCode()
    {
        return Objects.hash(kValue, tValue);
    }

    public static <K, T> Pair<K, T> of(K first, T second) {
        return new Pair(first, second);
    }

    public void ifPresent(BiConsumer<? super K, ? super T> biConsumer) {
        if (kValue != null && tValue != null)
            biConsumer.accept(kValue, tValue);
    }
}