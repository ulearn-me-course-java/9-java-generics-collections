package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public final class Pair<T1, T2> {

    private final T1 first;
    private final T2 second;

    private Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    public static <T1, T2> Pair<T1, T2> of(T1 value1, T2 value2) {
        return new Pair<T1, T2>(value1, value2);
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

    public void ifPresent(BiConsumer<? super T1, ? super T2> consumer){
        if (first != null && second != null)
            consumer.accept(first, second);
    }

}
