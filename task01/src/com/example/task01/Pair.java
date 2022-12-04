package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;


public class Pair<T, X>
{
    // TODO напишите реализацию
    private final T tValue;
    private final X xValue;

    private Pair(T tValue, X xValue)
    {
        this.tValue = tValue;
        this.xValue = xValue;
    }

    public static <T, X> Pair<T, X> of(T tValue, X xValue)
    {
        return new Pair<>(tValue, xValue);
    }

    public T getFirst()
    {
        return tValue;
    }

    public X getSecond()
    {
        return xValue;
    }

    public boolean equals(Pair<T, X> otherPair)
    {
        return tValue == otherPair.tValue && xValue == otherPair.xValue;
    }

    @Override
    public int hashCode()
    {
        return Objects.hash(tValue, xValue);
    }


    public void ifPresent(BiConsumer<? super T, ? super X> consumer)
    {
        if (tValue != null && xValue != null)
            consumer.accept(tValue, xValue);
    }
}
