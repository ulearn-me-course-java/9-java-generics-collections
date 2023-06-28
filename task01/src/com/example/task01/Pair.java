package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<F,S> {
    private F first;
    private S second;
    private Pair(F first,S second){
        this.first = first;
        this.second = second;
    }
    public static <F, S> Pair<F,S> of(F first, S second){
        return new Pair<F,S>(first,second);
    }
    public F getFirst() {
        return first;
    }
    public S getSecond(){
        return second;
    }
    public void ifPresent(BiConsumer<? super F,? super S> consumer) {
        if (this.first != null && this.second != null){
            consumer.accept((F) this.first,(S) this.second);
        }
    }
    @Override
    public int hashCode() {
        return super.hashCode();
    }
    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) object;
        return this.first.equals(pair.first) && this.second.equals(pair.second);
    }
}
