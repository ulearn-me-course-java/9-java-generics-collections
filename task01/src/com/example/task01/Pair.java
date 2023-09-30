package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Pair <T,E> {
    private final T first;
    private final E second;
    private Pair(T first, E second){
        this.first = first;
        this.second = second;
    }
    public T getFirst(){
        return first;
    }
    public E getSecond(){
        return second;
    }
    public boolean equals(Pair<T,E> pair){
        if (first==pair.getFirst() && second==pair.getSecond()){
            return true;
        }
        return false;
    }
    public int hashCode(){
        return Objects.hash(first,second);
    }
    public static <T,E> Pair <T,E> of(T first, E second){
        return new Pair<>(first,second);
    }
    public void ifPresent(BiConsumer<? super T,? super E> biConsumer){
        if (first!=null && second!=null){
            biConsumer.accept(first,second);
        }
    }
}
