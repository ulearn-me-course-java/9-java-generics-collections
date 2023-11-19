package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<K,V> {
    private K firstType;
    private V secondType;

    private Pair(K firstType, V secondType){
        this.firstType = firstType;
        this.secondType = secondType;
    }

    public K getFirst(){
        return firstType;
    }

    public V getSecond(){
        return secondType;
    }
    public static <K,V> Pair<K,V> of(K firstType, V secondType){
        return new Pair<K,V>(firstType, secondType);
    }

    public void ifPresent(BiConsumer biConsumer){
        if (firstType!=null&& secondType!=null){
            biConsumer.accept((K)firstType, (V)secondType);
        }
    }

    public int hashCode() {
        return Objects.hash(firstType, secondType);
    }
    public boolean equals(Object obj){
        if (getClass() != obj.getClass() || obj == null){
            return false;
        }
        if (this == obj) {
            return true;
        }
        Pair<?, ?> pair = (Pair<?, ?>) obj;

        return this.firstType.equals(pair.firstType)&&this.secondType.equals(pair.secondType);
    }
}
