package com.example.task01;

import java.util.function.BiConsumer;

public final class Pair<T,S> {

    private final T firstValue;
    private final S secondValue;

    private Pair(T f, S s){
        this.firstValue = f;
        this.secondValue = s;
    };

    public static <T,S> Pair<T,S> of(T f, S sec ){
        return new Pair<>(f,sec);
    }

    //Реализуйте методы getFirst(), getSecond(), equals() и hashCode(),
    public T getFirst(){
        return this.firstValue;
    }
    public S getSecond(){
        return this.secondValue;
    }

    public void ifPresent(BiConsumer<? super T, ? super S> consumer ){
        if(firstValue != null && secondValue != null){
            consumer.accept(firstValue, secondValue);
        }

    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Pair   ){
            return firstValue.equals( ((Pair<?, ?>) obj).firstValue) &&
                    secondValue.equals(((Pair<?, ?>) obj).secondValue);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return (firstValue.hashCode() + secondValue.hashCode()) * 3;
    }
}
