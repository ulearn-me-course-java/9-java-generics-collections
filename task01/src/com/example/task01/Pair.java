package com.example.task01;
import java.util.function.BiConsumer;

public class Pair<T, U> {
    private final T firstValue;
    private final U secondValue;

    private Pair(T firstValue, U secondValue) {
        this.firstValue = firstValue;
        this.secondValue=secondValue;
    }

    public T getFirst(){
        return firstValue;
    }

    public U getSecond(){
        return secondValue;
    }

    public static <T, U> Pair<T, U> of(T first, U second){
        return new Pair<>(first,second);
    }

    public void ifPresent( BiConsumer consumer){
        if(firstValue==null||secondValue==null)
            return;
        consumer.accept(firstValue,secondValue);
    }

    public boolean equals(Pair<T, U> pair){
        return firstValue==pair.firstValue && secondValue==pair.secondValue;
    }

    public int hashCode(){
        return firstValue.hashCode()+secondValue.hashCode()*31;
    }
}
