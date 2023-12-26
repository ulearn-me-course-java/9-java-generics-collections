package com.example.task01;

import java.util.*;
import java.util.function.BiConsumer;

public class Pair<K, V> {
    public K first;
    public V second;

    private Pair(K firstValue, V secondValue) {
        first = firstValue;
        second = secondValue;
    }

    public static <T, V> Pair<T, V> of(T firstValue, V secondValue) {
        return new Pair<>(firstValue, secondValue);
    }

    public K getFirst() {
        return first;
    }

    public V getSecond() {
        return second;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Pair<K, V> pair = (Pair<K, V>) obj;
        return this.first.equals(pair.first) && this.second.equals(pair.second);
    }

    public int hashCode() {
        return Objects.hash(this.first, this.second);
    }

    public void ifPresent(BiConsumer consumer) {
        if (this.first != null && this.second != null) {
            consumer.accept(this.first, this.second);
        }
    }
}
