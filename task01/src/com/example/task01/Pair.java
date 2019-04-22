package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair <F,S> {
    private final F first;
    private final S second;

    private Pair (F first, S second){
        this.first = first;
        this.second = second;
    }

    public static <F,S> Pair of (F first, S second){
        return new Pair<>(first, second);
    }

    public F getFirst(){
        return first;
    }

    public S getSecond(){
        return second;
    }

    public void ifPresent(BiConsumer<? super F, ? super S> biConsumer){
        if (first != null && second != null){
            biConsumer.accept(first, second);
        }
    }

    public boolean equals(Pair<F,S> other) {
        return (other.getFirst() == first && other.getSecond() == second);
    }

    public int hashCode(){
        return (int)Math.pow(Objects.hashCode(first), 2)*Objects.hashCode(second);
    }
}
