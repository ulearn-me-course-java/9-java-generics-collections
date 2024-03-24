package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair <T, Y>{
    public T key;
    public T getFirst(){
        return this.key;
    }
    public Y value;
    public Y getSecond(){
        return this.value;
    }
    private Pair(T key, Y value){
        this.key = key;
        this.value = value;
    }
    public static <T, Y> Pair<T, Y> of(T first, Y second){
        return new Pair(first, second);
    }
    public boolean equals(Pair second){
        return this.getFirst() == second.getFirst() && this.getSecond() == second.getSecond();
    }
    public int hashCode(){
        return Objects.hashCode(key) + Objects.hashCode(value);
    }
    public void ifPresent(BiConsumer<? super T, ? super Y> consumer) {
        if (key != null && value != null) {
            consumer.accept(key, value);
        }
    }
}
