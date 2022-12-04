package com.example.task01;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Pair<X, Y> {
    private X firstValue;
    private Y secondValue;

    private Pair(X firstValue, Y secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public X getFirst(){
        return firstValue;
    }

    public Y getSecond(){
        return secondValue;
    }
    @Override
    public int hashCode() {
        return Objects.hash(firstValue, secondValue);
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || this.getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(firstValue, pair.firstValue) && Objects.equals(secondValue, pair.secondValue);
    }

    public static <X, Y> Pair<X, Y> of(X first, Y second){
        return new Pair<X, Y>(first, second);
    }



    public void ifPresent(BiConsumer<? super X, ? super Y> consumer)
    {
        if (firstValue != null && secondValue != null)
            consumer.accept(this.firstValue, this.secondValue);
    }



}
