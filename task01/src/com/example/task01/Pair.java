package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public class Pair<T1, T2> {
    private T1 value1;
    private T2 value2;

    private Pair(T1 v1, T2 v2) {
        this.value1 = v1;
        this.value2 = v2;
    }

    public T1 getFirst() {
        return value1;
    }

    public T2 getSecond() {
        return value2;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || this.getClass() != obj.getClass())
            return false;
        Pair<?, ?> el = (Pair<?, ?>) obj;
        return this.value1.equals(el.value1) && this.value2.equals(el.value2);
    }

    public int hashCode() {
        return 2 * Objects.hashCode(value1) + Objects.hashCode(value2);
    }


    public static <T1, T2> Pair<T1, T2> of(T1 v1, T2 v2) {
        return new Pair<>(v1, v2);
    }

    public void ifPresent(BiConsumer<? super T1, ? super T2> s) {
        if (value1 != null && value2 != null)
            s.accept(value1, value2);
    }

}
