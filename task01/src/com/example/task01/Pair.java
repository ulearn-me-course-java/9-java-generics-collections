package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T, U> {
    private final T first;
    private final U second;

    T getFirst() { return first; }
    U getSecond(){ return second; }

    private Pair(T first, U second){
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj == null || obj.getClass() != this.getClass())
            return false;
        Pair<?, ?> objAsPair = (Pair<?, ?>) obj;
        return Objects.equals(first, objAsPair.first) &&
                Objects.equals(second, objAsPair.second);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(first)*Objects.hashCode(second);
    }

    public  static <T, U> Pair of(T first, U second){
        return new Pair<>(first, second);
    }

    public void ifPresent(BiConsumer<? super T, ? super U> consumer){
        if(first != null && second != null) consumer.accept(first, second);
    }
}
