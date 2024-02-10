package com.example.task01;
import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<a1, a2> {
    private a1 o1;
    private a2 o2;


    private Pair(a1 o1, a2 o2) {
        this.o1 = o1;
        this.o2 = o2;
    }

    public static <T1, T2> Pair<T1, T2> of(T1 o1, T2 o2) {
        return new Pair<>(o1, o2);
    }

    public void ifPresent(BiConsumer biConsumer){
        if(o1 == null || o2 == null) return;
        biConsumer.accept(o1, o2);
    }

    public a1 getFirst() {
        return o1;
    }

    public a2 getSecond() {
        return o2;
    }

    public <T3, T4> boolean equals(Pair<T3, T4> pair) {
        if(this == pair) return true;
        return o1.equals(pair.getFirst()) && o2.equals(pair.getSecond());
    }

    public int hashCode() {
        return Objects.hash(o1, o2);
    }
}
