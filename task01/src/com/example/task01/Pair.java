package com.example.task01;

import java.util.NoSuchElementException;
import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair <T, K> {
    private final T firstValue;
    private final K secondValue;

    private Pair(T firstValue, K secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public static <T, K> Pair<T, K> of(T firstValue, K secondValue) {
        return new Pair<T, K>(firstValue, secondValue);
    }

    public void ifPresent(BiConsumer<? super T, ? super K> consumer){
        if(firstValue != null && secondValue != null){
            consumer.accept(firstValue, secondValue);
        }
    }

    public T getFirst() {
        return  firstValue;
    }

    public K getSecond() {
        return  secondValue;
    }


    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        if(obj == null || getClass() != obj.getClass()){
            return false;
        }
        Pair<?,?> objP = (Pair<?, ?>) obj;
        return objP.firstValue.equals(firstValue) && objP.secondValue.equals(secondValue);
    }

    @Override
    public int hashCode(){
        return Objects.hash(firstValue, secondValue);
    }
}
