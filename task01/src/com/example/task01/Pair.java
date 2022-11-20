package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair <T,U> {
    private T firstElem;
    private U secondElem;

    private Pair(T firstElem, U secondElem){
        this.firstElem = firstElem;
        this.secondElem = secondElem;
    }

    public T getFirst() {
        return firstElem;
    }

    public U getSecond(){
        return secondElem;
    }

    public static <T,U> Pair<T,U> of(T first, U second){
        return new Pair<T,U>(first, second);
    }

    public void ifPresent(BiConsumer biConsumer){
        if(firstElem != null && secondElem != null){
            biConsumer.accept(firstElem, secondElem);
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(firstElem, pair.firstElem) && Objects.equals(secondElem, pair.secondElem);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstElem, secondElem);
    }
}
