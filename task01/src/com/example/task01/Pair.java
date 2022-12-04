package com.example.task01;

import java.util.function.BiConsumer;

public class Pair <T, V> {
    private final T value1;
    private final V value2;

    private Pair(T value1, V value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public T getFirst() {
        return value1;
    }

    public V getSecond() {
        return value2;
    }

    public boolean equals(Pair<T, V> obj) {
        return (this.value2 == obj.value2 && this.value1 == obj.value1);
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + value1.hashCode();
        result = prime * result + value2.hashCode();
        return result;
    }

    public static <T, V> Pair<T, V> of (T value1, V value2) {
        return new Pair<>(value1, value2);
    }

    public void ifPresent(BiConsumer<? super T,? super V> biConsumer) {
        if (value1 != null && value2 != null)
            biConsumer.accept(value1, value2);
    }
}
