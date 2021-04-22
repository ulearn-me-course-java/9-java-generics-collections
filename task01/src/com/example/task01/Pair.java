package com.example.task01;

import java.util.Objects;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

public final class Pair<T,D> {
    private final T valueT;
    private final D valueD;

    private Pair (T valueT,D valueD) {
        this.valueT = valueT;
        this.valueD = valueD;
    }

    public static <T,D> Pair<T,D> of (T valueT, D valueD) {
        return new Pair<>(valueT,valueD);
    }

    public T getFirst() {
        return valueT;
    }
    public D getSecond() {
        return  valueD;
    }

    public boolean equals(Object o) {
        if (!(o instanceof Pair)) {
            return false;
        }
        Pair<?,?> p = (Pair<?,?>) o;
        return Objects.equals(p.valueT,valueT) && Objects.equals(p.valueD,valueD);
    }
    public int hashCode() {
        return ((valueT == null ? 0 : valueT.hashCode()) ^ (valueD == null ? 0 : valueD.hashCode()));
    }
    public boolean ifPresent() {
        if (valueT != null && valueD != null) {
            return true;
        } else { return false; }
    }
    public void ifPresent(BiConsumer<? super T,? super D> consumer) {
        if (valueT != null && valueD != null) {
            consumer.accept(valueT,valueD);
        }
    }
}
