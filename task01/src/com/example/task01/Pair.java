package com.example.task01;

public class Pair<T, V> {
    private final T value1;
    private final V value2;

    private Pair(T value1, V value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public static <T, V> Pair<T, V> of(T value1, V value2) {
        return new Pair<>(value1, value2);
    }

    public T getFirst() {
        return value1;
    }

    public V getSecond() {
        return value2;
    }

    public void ifPresent(java.util.function.BiConsumer consumer) {
        if (value1 != null && value2 != null)
            consumer.accept(value1, value2);
    }

    @Override
    public boolean equals(Object obj) {
        Pair<?, ?> pair = (Pair<?, ?>) obj;
        return value1.equals(pair.value1) && value2.equals(pair.value2);
    }

    @Override
    public int hashCode() {
        return value1.hashCode() * 397 ^ value2.hashCode();
    }
}
