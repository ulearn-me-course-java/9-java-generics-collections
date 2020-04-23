package com.example.task01;

import java.util.function.BiConsumer;

public class Pair<F,S> {
    private F first;
    private S second;

    public F getFirst(){
        return first;
    }

    public S getSecond(){
        return second;
    }

    private Pair(F first, S second){
        this.first = first;
        this.second = second;
    }

    public boolean equals(Pair<F,S> pair){
        return first.equals(pair.getFirst()) && second.equals(pair.getSecond());
    }

    public int hashCode(){
        return first.hashCode() % 231 + second.hashCode()%10;
    }

    public static <F,S> Pair<F,S> of(F first, S second){
        return new Pair<>(first, second);
    }

    public void ifPresent(BiConsumer<? super F, ? super S> biConsumer) {
        if (first != null && second != null)
            biConsumer.accept(first, second);
    }

}
