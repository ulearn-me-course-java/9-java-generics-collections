package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<F, S> {

    public final F firstValue;
    public final S secondValue;

    private Pair(F firstValue, S secondValue){
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    public static <F, S> Pair<F, S> of(F firstValue, S secondValue){
        return new Pair<>(firstValue, secondValue);
    }
    public F getFirst(){
        return firstValue;
    }

    public S getSecond(){
        return secondValue;
    }

    @Override
    public boolean equals(Object object){
        if (this == object){
            return true;
        }
        if ((object == null) || (getClass() != object.getClass())){
            return false;
        }
        Pair<?, ?> pair = (Pair<?, ?>) object;
        return Objects.equals(firstValue, pair.firstValue) && Objects.equals(secondValue, pair.secondValue);
    }

    @Override
    public int hashCode(){
        return Objects.hash(firstValue, secondValue);
    }

    public void ifPresent(BiConsumer biConsumer){
        if (firstValue != null && secondValue != null){
            biConsumer.accept(firstValue, secondValue);
        }
    }
}
