package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair<T, U> {
    private final T first;
    private final U second;

    private Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }

    public T getFirst() {
        return first;
    }

    public U getSecond() {
        return second;
    }

    public boolean equals(Pair pair) {
        if (this == pair) {
            return true;
        }
        if (!(pair instanceof Pair)) {
            return false;
        }
        boolean first = Objects.equals(this.first, pair.first);
        boolean second = Objects.equals(this.second, pair.second);
        return (first && second);
    }

    /***
     * Вычисляет hashCode() для левого и для правого элемента пары по-отдельности, возвращает их сумму.
     * Чтобы сумма хэшкодов не превышала допустимое значение (+- 2^31-1), дл каждой части берется остаток от деления на 2^30
     * @return
     */
    public int hashCode() {
        int first_hash = Objects.hashCode(first) % (int) Math.pow(2, 30);
        int second_hash = Objects.hashCode(second) % (int) Math.pow(2, 30);
        return first_hash+second_hash;
    }

    public static <T, U> Pair<T, U> of(T first, U second) {
        return new Pair<>(first, second);
    }

    public void ifPresent(BiConsumer<? super T, ? super U> biConsumer) {
        if (first != null && second != null) {
            biConsumer.accept(first, second);
        }
    }
}
