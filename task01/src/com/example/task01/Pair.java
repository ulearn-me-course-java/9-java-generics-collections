package com.example.task01;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Pair <T1, T2>{
    private final T1 T1;
    private final T2 T2;

    private Pair(T1 T1, T2 T2) {
        this.T1 = T1;
        this.T2 = T2;
    }

    public T1 getFirst(){
        return T1;
    }

    public T2 getSecond(){
        return T2;
    }
    // что делает of? Создает объект пару с нужными параметрами
    public static <T1,T2> Pair<T1,T2> of(T1 first, T2 second){
        return new Pair<>(first, second);
    }

    public void ifPresent(BiConsumer<? super T1, ? super T2> consumer) {
        if (T1 != null && T2 != null) {
            consumer.accept(T1, T2);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(T1, pair.T1) && Objects.equals(T2, pair.T2);
    }

    @Override
    public int hashCode() {
        return Objects.hash(T1, T2);
    }
}
