package com.example.task01;

import java.util.Objects;

public class Pair<T1, T2> {
    private T1 first;
    private T2 second;

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    private Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this)
            return true;

        if (!(obj instanceof Pair))
            return false;

        Pair<?,?> other = (Pair<?,?>) obj;
        return Objects.equals(first, other.first) && Objects.equals(second, other.second);
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    public static <T1,T2> Pair<T1,T2> of(T1 first, T2 second){
        return new Pair<>(first, second);
    }

    public void ifPresent( java.util.function.BiConsumer<? super T1, ? super T2> consumer){
        if (first != null && second != null){
            consumer.accept(first,second);
        }
    }
}
