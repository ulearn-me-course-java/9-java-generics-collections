package com.example.task01;

import java.util.function.BiConsumer;

public class Pair<T1, T2> {
    private final T1 value1;
    private final T2 value2;

    private Pair(T1 value1, T2 value2) {
        this.value1 = value1;
        this.value2 = value2;
    }

    public T1 getFirst() {
        return value1;
    }

    public T2 getSecond() {
        return value2;
    }

    public boolean equals(Pair<T1, T2> obj) {
        return (this.value2 == obj.value2 && this.value1 == obj.value1);
    }

    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + value1.hashCode();
        result = prime * result + value2.hashCode();
        return result;
    }

    public static <T1, T2> Pair<T1, T2> of(T1 value1, T2 value2) {
        return new Pair<>(value1, value2);
    }

    public void ifPresent(BiConsumer<? super T1,? super T2> biConsumer) {
        if (value1 != null && value2 != null)
            biConsumer.accept(value1, value2);
    }
}
