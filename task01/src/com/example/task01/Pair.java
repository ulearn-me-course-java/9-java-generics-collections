package com.example.task01;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Pair<T, U> {
    private final T tValue;
    private final U uValue;

    private Pair(T tValue, U uValue) {
        this.tValue = tValue;
        this.uValue = uValue;
    }

    public static <T, U> Pair<T, U> of(T tValue, U uValue) {
        return new Pair(tValue, uValue);
    }


    public T getFirst() {
        return tValue;
    }

    public U getSecond() {
        return uValue;
    }

    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }

        if(!(obj instanceof Pair)){
            return false;
        }

        Pair<?, ?> other = (Pair<?, ?>) obj;
        if(Objects.equals(tValue, other.tValue) && Objects.equals(uValue, other.uValue))
            return true;
        return false;
    }

    public int hashCode() {
        return Objects.hash(tValue, uValue);
    }

    public void ifPresent(BiConsumer<? super T, ? super U> biConsumer) {
        if (tValue != null && uValue != null)
            biConsumer.accept(tValue, uValue);
    }
}
