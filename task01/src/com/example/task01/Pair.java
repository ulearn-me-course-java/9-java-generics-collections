package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair <T, S> {
    private T first;
    private S second;
    private Pair(T first, S second){
        this.first = first;
        this.second = second;
    }

    public static <T, S> Pair<T, S> of(T first, S second) {
        return new Pair<T, S>(first, second);
    }

    public T getFirst(){
        return first;
    }

    public S getSecond(){
        return second;
    }

    public void ifPresent(BiConsumer consumer){
        if (first != null && second != null)
            consumer.accept((T) first,(S)second);
    }

    @Override
    public boolean equals(Object object){
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        Pair<?, ?> pair = (Pair<?, ?>) object;

        return pair.first.equals(first) && pair.second.equals(second);
    }

    @Override
    public int hashCode(){
        return Objects.hash(first, second);
    }
}
