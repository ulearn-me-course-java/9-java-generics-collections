package com.example.task01;

import java.util.function.BiConsumer;

public class Pair<F, S> {
    private final F first;
    private final S second;

    private Pair(F first, S second){
        this.first = first;
        this.second = second;
    }


    public F getFirst() {
        return first;
    }

    public S getSecond() {
        return second;
    }

    public static <F, S> Pair<F, S> of(F first, S second){
        return new Pair(first, second);
    }

    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof Pair)) return false;

        Pair other = (Pair) obj;

        return other.first.equals(this.first) && other.second.equals(this.second);
    }

    @Override
    public int hashCode() {
        return first.hashCode() + second.hashCode() * 1023;
    }

    public void ifPresent(BiConsumer<? super F, ? super S> cons){
        if(first != null && second != null)
            cons.accept(first, second);
    }

}
