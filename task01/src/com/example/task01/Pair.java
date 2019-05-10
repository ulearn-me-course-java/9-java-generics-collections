package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T1,T2> {
    private T1 value1;
    private T2 value2;

    public T1 getFirst() {
        return value1;
    }

    public T2 getSecond() {
        return value2;
    }

    private Pair(T1 value1, T2 value2) {
        this.value1 = value1;
        this.value2 = value2;
    }


    public static <T1, T2> Pair<T1, T2> of(T1 value1, T2 value2) {
        return new Pair<>(value1, value2);
    }

    public void ifPresent(BiConsumer<? super T1, ? super T2> consumer) {
        if (value1 != null && value2 != null)
            consumer.accept(value1, value2);
    }

    
    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;

        if (!(obj instanceof Pair))
            return false;

        Pair<?, ?> pair = (Pair<?, ?>) obj;

        return (pair.getFirst() == getFirst() && pair.getSecond() == getSecond());
    }

    @Override
    public int hashCode() {
        return (Objects.hashCode(value1) * 10) + Objects.hashCode(value2);
    }
}
