package com.example.task01;

import java.util.function.BiConsumer;

public class Pair <T1, T2> {
    private T1 first;
    private T2 second;

    private Pair(T1 first, T2 second){
        this.first = first;
        this.second = second;
    }

    public T1 getFirst(){
        return first;
    }
    public T2 getSecond(){
        return second;
    }

    public boolean equals(Pair<T1, T2> pair){
        return first == pair.first && second == pair.second;
    }

    public int hashCode(){
        return first.hashCode() + second.hashCode();
    }

    static <T1, T2> Pair<T1, T2> of(T1 first1 , T2 second2 ){
        return new Pair<>(first1, second2 );
    }

    public void ifPresent( BiConsumer consumer){
        if(first == null || second == null){
            return;
        }
        consumer.accept(first, second);
    }

}
