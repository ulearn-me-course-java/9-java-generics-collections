package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T, E> {

    private final T firstArg;
    private final E secondArg;

    private Pair(T firstArg, E secondArg) {
        this.firstArg = firstArg;
        this.secondArg = secondArg;
    }

    public T getFirst() {
        return firstArg;
    }

    public E getSecond() {
        return secondArg;
    }

    public void ifPresent(BiConsumer<? super T, ? super E> consumer) {
        if(firstArg != null && secondArg != null)
            consumer.accept(firstArg, secondArg);
    }

    public static <T, E> Pair<T, E> of(T firstArg, E secondArg) {
        return new Pair<>(firstArg, secondArg);
    }

    @Override
    public boolean equals(Object obj) {
        Pair arg = (Pair)obj;
        if(obj instanceof Pair)
            return (arg.firstArg == firstArg && arg.secondArg == secondArg);
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstArg, secondArg);
    }
}
