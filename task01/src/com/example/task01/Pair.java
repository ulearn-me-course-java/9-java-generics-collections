package com.example.task01;


import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Pair<T, G> {
    private final T first;
    private final G second;

    private Pair(T first, G second) {
        this.first = first;
        this.second = second;
    }
    public static <T,G> Pair<T, G> of(T first, G second){
        return new Pair<>(first, second);
    }

    public T getFirst() {
        return first;
    }

    public G getSecond() {
        return second;
    }

    public void ifPresent(BiConsumer<? super T, ? super G> consumer) {
        if (first != null && second != null){
            consumer.accept(first, second);
        }
    }

    public boolean equals(Pair<T, G> pair) {
        return (first == pair.getFirst() & second == pair.getSecond());
    }

    @Override
    public int hashCode(){
        return Objects.hash(first, second);
    }
}