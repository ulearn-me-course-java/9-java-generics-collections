package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair <dT, sT> {
    // TODO напишите реализацию
    private final dT dValue;
    private final sT sValue;

    private Pair (dT iValue, sT sValue) {
        this.dValue = iValue;
        this.sValue = sValue;
    }

    public dT getFirst() {
        return dValue;
    }

    public sT getSecond() {
        return sValue;
    }

    @Override
    public int hashCode() {
        return Objects.hash(dValue,sValue);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        Pair <dT,sT> pair = (Pair<dT,sT>) obj;
        return (this.dValue == pair.dValue && this.sValue == pair.sValue);
    }

    public static <dT, sT> Pair <dT, sT> of (dT dValue, sT sValue) {
        return  new Pair<>(dValue, sValue);
    }

    public void ifPresent(BiConsumer biConsumer) {
        if(dValue != null && sValue != null){
            biConsumer.accept(dValue, sValue);
        }
    }
}
