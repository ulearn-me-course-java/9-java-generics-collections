package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T, V> {
    // TODO напишите реализацию
    public T first;
    public V second;
    private Pair(T firstValue, V secondValue){
        first = firstValue;
        second = secondValue;
    }
   public static <T, V> Pair<T, V> of(T firstValue, V secondValue){
        return new Pair<>(firstValue, secondValue);
    }
    public T getFirst(){
        return first;
    }
    public V getSecond(){
        return second;
    }
    public boolean equals(Object obj){
        if (this == obj) return true;
        if (obj == null || this.getClass() != obj.getClass()) return false;
        Pair<T,V> pair = (Pair<T, V>) obj;
        return this.first.equals(pair.first) && this.second.equals(pair.second);
    }
    public int hashCode(){
        return Objects.hash(this.first,this.second);
    }
    public void ifPresent(BiConsumer consumer) {
        if (this.first != null && this.second != null) {
            consumer.accept((T) this.first, (V) this.second);
        }
    }
}
