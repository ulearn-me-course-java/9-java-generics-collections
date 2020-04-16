package com.example.task01;

import java.util.function.BiConsumer;

public class Pair<T, U> {
    private final T first;
    private final U second;

    public Pair(T first, U second){
        this.first = first;
        this.second = second;
    }

    public T getFirst(){
        return first;
    }

    public U getSecond(){
        return second;
    }

    public static <T, U> Pair<T, U> of(T first, U second){
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
        return first.hashCode() + second.hashCode() * 2047;
    }

    public void ifPresent(BiConsumer<? super T, ? super U> cons){
        if(first != null && second != null)
            cons.accept(first, second);
    }
}
