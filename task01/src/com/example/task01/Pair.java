package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<F,S> {

    private F first;
    private S second;

    public static <F, S> Pair<F, S> of(F first, S second) {
        return new Pair<>(first, second);
    }

    private Pair(F first, S second) {
        this.first = first;
        this.second = second;
    }
    public F getFirst(){
        return first;
    }

    public S getSecond(){
        return second;
    }

    @Override
    public int hashCode(){

        return Objects.hash(first,second);
    }

    @Override
    public boolean equals(Object object) {
        if (getClass() != object.getClass()) {
            return false;
        }
        Pair<?, ?> pair = (Pair<?, ?>) object;
        return pair.first.equals(first) && pair.second.equals(second);
    }

    public void ifPresent(BiConsumer<? super F, ? super S> biConsumer) {
        if (first!= null && second != null) {
            biConsumer.accept(first, second);
        }
    }
}
