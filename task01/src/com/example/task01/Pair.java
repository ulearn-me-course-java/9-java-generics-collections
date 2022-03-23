package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<TKey, TValue> {
    TKey first;
    TValue second;

    private Pair(TKey key, TValue value) {
        this.first = key;
        this.second = value;
    }

    public TKey getFirst() {
        return this.first;
    }

    public TValue getSecond() {
        return this.second;
    }

    public static Pair of(Object f, Object s) {
        return new Pair(f, s);
    }

    public void ifPresent(BiConsumer consumer) {
        if(this.first != null && this.second != null) {
            if(consumer != null) {
                consumer.accept(first,second);
                System.out.println(first);
                System.out.println(second);
            }
        }
    }
    @Override
    public boolean equals(Object o) {
        Pair obj = (Pair)o;
        if(o instanceof Pair) {
            return this.hashCode() == o.hashCode() &&
                    (this.first == obj.first && this.second == obj.second);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }
}
