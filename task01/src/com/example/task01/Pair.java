package com.example.task01;

import java.lang.reflect.Type;
import java.net.Proxy;
import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T1, T2> {
    private final T1 o1;
    private final T2 o2;

    private Pair(T1 o1, T2 o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    public static <T1, T2> Pair<T1, T2> of(T1 o1, T2 o2) {
        return new Pair<>(o1, o2);
    }

    public void ifPresent(BiConsumer<? super T1, ? super T2> biConsumer) {
        if (o1 == null || o2 == null) return;
        biConsumer.accept(o1, o2);
    }

    public T1 getFirst() {
        if (o1 == null) {
            throw new NoSuchElementException();
        }

        return o1;
    }

    public T2 getSecond() {
        if (o2 == null) {
            throw new NoSuchElementException();
        }

        return o2;
    }

    public <T3, T4> boolean equals(Pair<T3, T4> pair) {
        if (this == pair) return true;
        return o1.equals(pair.getFirst()) && o2.equals(pair.getSecond());
    }

    public int hashCode() {
        return Objects.hash(o1, o2);
    }
}
