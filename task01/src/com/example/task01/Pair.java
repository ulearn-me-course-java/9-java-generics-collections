package com.example.task01;



import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T, K> {
    private T firstElem;
    private K secondElem;

    private Pair(T firstElem, K secondElem){
        this.firstElem = firstElem;
        this.secondElem = secondElem;
    }
    public T getFirst(){
        return firstElem;
    }
    public K getSecond(){
        return secondElem;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof Pair)) {
            return false;
        }
        Pair<?, ?> otherPair = (Pair<?, ?>) obj;
        return Objects.equals(firstElem, otherPair.getFirst()) && Objects.equals(secondElem, otherPair.getSecond());
    }

    public int hashCode(){
        int hash = firstElem.hashCode();
        return hash * 31 + secondElem.hashCode();
    }


    public static <T, K> Pair<T, K> of(T firstElem, K secondElem) {
        return new Pair<>(firstElem, secondElem);
    }

    public void ifPresent(BiConsumer<? super T, ? super K> action) {
        if (firstElem != null && secondElem != null) {
            action.accept(firstElem, secondElem);
        }
    }
}
