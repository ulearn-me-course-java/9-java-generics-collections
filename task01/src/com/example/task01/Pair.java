package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Pair <T,K> {
    private final T first;
    private final K second;

    public T getFirst(){
        return first;
    }

    public K getSecond()
    {
        return second;
    }

    private Pair(T first, K second)
    {
        this.first = first;
        this.second = second;
    }

    public static <T,K> Pair<T,K> of(T first, K second){
        return new Pair<>(first,second);
    }

    public void ifPresent(BiConsumer<? super T, ? super K> consumer){
        if (this.first != null && this.second != null)
            consumer.accept(this.first, this.second);
    }

    public boolean equals(Pair newPair){
        return newPair.first==this.first && newPair.second==this.second;
    }
    @Override public int hashCode(){
        return Objects.hash(this.first,this.second);
    }

}
