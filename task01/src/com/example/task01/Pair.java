package com.example.task01;

import java.util.function.BiConsumer;

public class Pair<T, U> {
    private final T tValue;
    private final U uValue;

    public static <T, U> Pair<T, U> of (T param1, U param2){
        return new Pair(param1, param2);
    }

    public void ifPresent(BiConsumer consumer){
        if (tValue != null && uValue != null) consumer.accept(tValue, uValue);
    }

    public T getFirst(){
        return tValue;
    }

    public U getSecond(){
        return uValue;
    }

    @Override
    public int hashCode(){
        final int prime = 37;
        int result = 1;
        result = result * prime * tValue.hashCode();
        result = result * prime * uValue.hashCode();
        return result;
    }

    @Override
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (!(obj instanceof Pair)) return false;

        Pair p = (Pair) obj;
        return tValue.equals(p.tValue) && uValue.equals(p.uValue);
    }

    private Pair(T tValue, U uValue) {
        this.tValue = tValue;
        this.uValue = uValue;
    }
}
