package com.example.task01;

import org.assertj.core.api.Assertions;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair <T1, T2> {
    private final T1 first;
    private final T2 second;
    private Pair(T1 firstValue, T2 secondValue)
    {
        this.first = firstValue;
        this.second = secondValue;
    }
    public static <T1,T2> Pair<T1,T2> of(T1 firstValue, T2 secondValue){
        return new Pair<>(firstValue, secondValue);
    }
    public T1 getFirst(){
        return this.first;
    }
    public T2 getSecond(){
        return this.second;
    }
    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Pair<T1,T2> pair = (Pair<T1, T2>) obj;
        return this.first.equals(pair.first) && this.second.equals(pair.second);
    }
    @Override
    public int hashCode(){
        return Objects.hash(this.first,this.second);
    }
    public void ifPresent(BiConsumer consumer) {
        if (this.first != null && this.second != null) {
            consumer.accept((T1) this.first, (T2) this.second);
        }
    }
}
