package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<F, S> {
    private F first;
    private S second;

    private Pair(F first, S second) {
        setFirst(first);
        setSecond(second);
    }

    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    public void setFirst(F first) {
        this.first = first;
    }

    public void setSecond(S second) {
        this.second = second;
    }

    public static <F, S> Pair<F, S> of(F first, S second) {
        return new Pair<>(first, second);
    }

    public void ifPresent(BiConsumer<? super F, ? super S> biConsumer) {
        if (getFirst() != null && getSecond() != null)
            biConsumer.accept(getFirst(), getSecond());
    }

    public boolean equals(Pair<F,S> pair) {
        return pair.getFirst().equals(this.getFirst()) && pair.getSecond().equals(this.getSecond());
    }

    public int hashCode() {
        return (int) (Objects.hashCode(getFirst())*Math.pow(Objects.hashCode(getSecond()),2)
                +Objects.hashCode(getSecond())*Math.pow(Objects.hashCode(getFirst()),2));
    }
}