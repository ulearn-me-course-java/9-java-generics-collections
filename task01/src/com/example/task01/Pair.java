package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair <F, S> {

    private final F first;
    private final S second;

    private Pair (F first, S second)
    {
        this.first = first;
        this.second = second;
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(first, pair.first) && Objects.equals(second, pair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    public static <F, S> Pair <F, S> of(F first, S second)
    {
            return new Pair(first, second);
    }

    public void ifPresent(BiConsumer<? super F, ? super S> biconsumer)
    {
        if(first != null && second != null)
            biconsumer.accept(first, second);
    }


}
