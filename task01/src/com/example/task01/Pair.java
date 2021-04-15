package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<E1, E2> {
    private final E1 e1;
    private final E2 e2;

    private Pair(E1 e1, E2 e2) {
        this.e1 = e1;
        this.e2 = e2;
    }

    public static <T1, T2> Pair<T1, T2> of(T1 e1, T2 e2) {
        return new Pair<>(e1, e2);
    }

    public E1 getFirst() {
        return e1;
    }

    public E2 getSecond() {
        return e2;
    }

    public void ifPresent(BiConsumer<? super E1, ? super E2> consumer) {
        if (e1 != null && e2 != null)
            consumer.accept(e1, e2);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(e1, pair.e1) &&
                Objects.equals(e2, pair.e2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(e1, e2);
    }
}
