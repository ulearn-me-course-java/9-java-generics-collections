package com.example.task01;

import java.util.Objects;
import java.util.Optional;

public class Pair <K, V>{

    private K key;
    private V value;

    private Pair(K key, V value){
        this.key = key;
        this.value = value;
    }

    public static <K, V> Pair<K, V> of(K key, V value){
        return new Pair<>(key, value);
    }

    public K getFirst(){
        return (K)key;
    }

    public V getSecond(){
        return (V)value;
    }

    public boolean equals(Pair<K, V> pair){
        return key == pair.getFirst() && this.value == pair.getSecond();
    }

    public int hashCode(){
        return 1019 * key.hashCode() * value.hashCode();
    }

    public void ifPresent(java.util.function.BiConsumer<? super K, ? super V> biConsumer){
        if(key != null && value != null)
            biConsumer.accept(key, value);
    }
}
