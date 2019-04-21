package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T,E> {
    private final T value1;

    private final E value2;

    private Pair(T value1, E value2){
        this.value1= value1;
        this.value2 = value2;
    }

    public static <T, E> Pair<T ,E> of(T locVal1, E locVal2){
        return new Pair<>(locVal1,locVal2);
    }

    public void ifPresent(BiConsumer<? super T, ? super E> consumer) {
        if (value1 != null && value2 != null)
            consumer.accept(value1, value2);
    }

    public T getFirst(){
        return value1;
    }

    public E getSecond(){
        return value2;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }

        if (!(obj instanceof Pair)) {
            return false;
        }

        Pair<?,?> other = (Pair<?,?>) obj;
        return Objects.equals(value1, other.value1) && Objects.equals(value2, other.value2);
    }
    @Override
    public int hashCode() {
        return Objects.hashCode(value1)*Objects.hashCode(value2);
    }
}
