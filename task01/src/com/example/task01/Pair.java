package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T, J> {
    private T firstValue;
    private J secondValue;

    public T getFirst() {
        return firstValue;
    }

    public J getSecond() {
        return secondValue;
    }


    public void setFirst(T value) {
        firstValue = value;
    }

    public void setSecond(J value) {
        secondValue = value;
    }

    public void ifPresent(BiConsumer consumer){
        if (firstValue != null && secondValue != null)
            consumer.accept((T) firstValue,(J)secondValue);
    }

    public static <T, J> Pair<T, J> of(T first, J second) {
        return new Pair<T,  J>(first, second);
    }

    private Pair(T firstValue, J secondValue) {
        this.firstValue = firstValue;
        this.secondValue = secondValue;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object)
            return true;
        if (object == null || getClass() != object.getClass())
            return false;
        //вопросик - любое значение дженерика
        Pair<?, ?> pair = (Pair<?, ?>) object;

        boolean isEqual = Objects.equals(firstValue, pair.firstValue) && Objects.equals(secondValue, pair.secondValue);

        return isEqual;
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstValue, secondValue);
    }
}
