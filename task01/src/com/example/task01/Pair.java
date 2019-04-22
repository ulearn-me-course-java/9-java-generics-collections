package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T1, T2> {
    private final T1 v1;
    private final T2 v2;

    private Pair(T1 t1, T2 t2){
        v1 = t1;
        v2 = t2;
    }

    public static <T1, T2> Pair<T1, T2> of(T1 t1, T2 t2){
        return new Pair<>(t1, t2);
    }

    public void ifPresent(BiConsumer<? super T1, ? super T2> consumer){
        if (v1 != null && v2 != null)
            consumer.accept(v1, v2);
    }

    public T1 getFirst(){
        return v1;
    }

    public T2 getSecond(){
        return v2;
    }

    public boolean equals(Object o){
        Pair<?, ?> tmp = (Pair<?, ?>) o;
        return Objects.equals(v1, tmp.v1) &&
                Objects.equals(v2, tmp.v2);
    }

    public int hashCode(){
        return Objects.hashCode(v1) * Objects.hashCode(v2);
    }
}
