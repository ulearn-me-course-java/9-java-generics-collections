package com.example.task01;
import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T, Y> {
    private final T first_val;
    private final Y second_val;

    private Pair(T tValue, Y xValue)
    {
        this.first_val = tValue;
        this.second_val = xValue;
    }

    public static <T, X> Pair<T, X> of(T first_val, X second_vaV)
    {
        return new Pair<>(first_val, second_vaV);
    }

    public T getFirst()
    {
        return this.first_val;
    }

    public Y getSecond()
    {
        return this.second_val;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pair<?, ?> pair = (Pair<?, ?>) o;
        return Objects.equals(this.first_val, pair.first_val) && Objects.equals(this.second_val, pair.second_val);
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(this.first_val, this.second_val);
    }


    public void ifPresent(BiConsumer<? super T, ? super Y> consumer)
    {
        if (this.first_val != null && this.second_val != null)
            consumer.accept(this.first_val, this.second_val);
    }
}
