package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T1, T2> {
    private final T1 valueFirst;
    private final T2 valueSecond;

    private Pair(T1 valueFirst, T2 valueSecond) {
        this.valueFirst = valueFirst;
        this.valueSecond = valueSecond;
    }

    public T1 getFirst() {
        return valueFirst;
    }

    public T2 getSecond() {
        return valueSecond;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(valueFirst, pair.valueFirst) && Objects.equals(valueSecond, pair.valueSecond);
    }

    @Override
    public int hashCode() {
        return Objects.hash(valueFirst, valueSecond);
    }

    public static <T1, T2> Pair<T1, T2> of(T1 first, T2 second) {
        return new Pair(first, second);
    }

    public void ifPresent(BiConsumer<? super T1, ? super T2> biConsumer) {
        if(valueFirst != null && valueSecond != null) {
            biConsumer.accept(valueFirst, valueSecond);
        }
    }
}
