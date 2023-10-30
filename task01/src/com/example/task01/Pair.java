package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Pair<T1, T2> {
    T1 firstValue;
    T2 secondValue;

    private Pair(T1 f, T2 s) {
        firstValue = f;
        secondValue = s;
    }

    public static <T1, T2> Pair<T1, T2> of(T1 f, T2 s) {
        return new Pair(f, s);
    }

    public T1 getFirst() {
        return firstValue;
    }

    public T2 getSecond() {
        return secondValue;
    }

    public void ifPresent(BiConsumer<? super T1, ? super T2> consumer) {
        if (firstValue != null && secondValue != null) {
            consumer.accept(firstValue, secondValue);
        }
    }


    //вставилось автоматом
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
}
