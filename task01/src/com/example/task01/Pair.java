package com.example.task01;

import com.github.javaparser.ast.visitor.ObjectIdentityHashCodeVisitor;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T1, T2> {
    private final T1 first;
    private final T2 second;

    private Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public static <T1, T2> Pair<T1, T2> of (T1 first, T2 second){
        return new Pair<>(first, second);
    }

    public T1 getFirst(){
        return first;
    }

    public T2 getSecond(){
        return second;
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj) return true;
        if(obj == null || getClass() != obj.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) obj;
        return first == pair.getFirst() && second == getSecond();
    }

    @Override
    public int hashCode(){
        return Objects.hash(first, second);
    }

    public void ifPresent(BiConsumer<? super T1, ? super T2> consumer){
        if(first != null && second != null)
            consumer.accept(first, second);
    }
}