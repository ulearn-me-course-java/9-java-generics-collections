package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<TFirst, TSecond> {
    private TFirst first;
    private TSecond second;

    private Pair(TFirst first, TSecond second){
        this.first = first;
        this.second = second;
    }

    TFirst getFirst() {
        return first;
    }

    TSecond getSecond(){
        return second;
    }

    public static <TFirst, TSecond> Pair<TFirst, TSecond> of (TFirst first, TSecond second){
        return new Pair<TFirst, TSecond>(first, second);
    }

    public int hashCode() {
        return (Objects.hashCode(this.first) << 16) ^ Objects.hashCode(this.second);
    }

    public boolean equals(Object object) {
        if (object == null)
            return false;

        if (object.getClass() != this.getClass())
            return false;

        Pair<?, ?> pair = (Pair<?, ?>) object;
        return Objects.equals(this.first, pair.first) && Objects.equals(this.second, pair.second);
    }

    public void ifPresent(BiConsumer<? super TFirst, ? super TSecond> consumer){
        if ((this.first != null) && (this.second != null))
            consumer.accept(this.first, this.second);
    }
}
