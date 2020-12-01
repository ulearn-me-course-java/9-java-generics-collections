package com.example.task01;

import java.util.function.BiConsumer;

public class Pair<F, S>
{
    private final F first;
    private final S second;

    private Pair(F first, S second)
    {
        this.first = first;
        this.second = second;
    }

    public static <F, S> Pair<F, S> of(F first, S second)
    {
        return new Pair<>(first, second);
    }

    public F getFirst()
    {
        return first;
    }

    public S getSecond()
    {
        return second;
    }

    public void ifPresent(BiConsumer<? super F, ? super S> pair)
    {
        if (first == null || second == null)
            return;
        pair.accept(first, second);
    }

    public boolean equals(Object obj)
    {
        if (obj == null || this.hashCode() != obj.hashCode())
            return false;
        if (this == obj)
            return true;
        if (getClass() != obj.getClass())
            return false;
        Pair<F, S> pair;
        try
        {
            pair = (Pair<F, S>) obj;
        }
        catch (Exception e)
        {
            return false;
        }
        return first.equals(pair.first) && second.equals(pair.second);
    }

    public int hashCode()
    {
        return 31 * first.hashCode() + second.hashCode();
    }
}
