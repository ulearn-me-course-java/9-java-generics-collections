package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair <T, U> {
    private final T first;
    private final U second;

    private Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public static <T,U> Pair<T,U> of (T first, U second){
        return new Pair<T,U>(first, second);
    }

    public T getFirst(){
        return first;
    }
    public  U getSecond(){
        return second;
    }

    public boolean equals(Object object){
        if (this == object)
            return true;
        if (!(object instanceof Pair)) return false;
        Pair pair = (Pair) object;
        return this.first.equals(pair.first) && this.second.equals(pair.second);
    }

    public int hashCode(){
        return Objects.hashCode(getFirst()) * 100 + Objects.hashCode(getSecond());
    }

    public void ifPresent(BiConsumer<? super T, ? super U> consumer){
        if (first != null && second != null)
            consumer.accept(first, second);
    }
}
