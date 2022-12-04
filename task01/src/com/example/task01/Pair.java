package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

/*
Реализуйте generic-класс Pair, похожий на Optional,
но содержащий пару элементов разных типов и не запрещающий элементам принимать значение null.
-Реализуйте методы getFirst(), getSecond(), equals() и hashCode(),
-Реализуйте статический фабричный метод of().
Конструктор должен быть закрытым (private).
-Реализуйте метод ifPresent, аналогичный одноименному методу класса Optional и принимающий java.util.function.BiConsumer
 */
public class Pair<T1, T2> {

    private final T1 first;
    private final T2 second;

    private Pair(T1 first, T2 second) {
        this.first = first;
        this.second = second;
    }

    public static <T1, T2> Pair<T1, T2> of(T1 first, T2 second) {
        return new Pair<>(first, second);
    }

    public T1 getFirst() {
        return first;
    }

    public T2 getSecond() {
        return second;
    }

    public boolean equals(Pair newPair) {
        return newPair.first == this.first
                && newPair.second == this.second;
    }

    @Override
    public int hashCode() {
        return Objects.hash(first, second);
    }

    public void ifPresent(java.util.function.BiConsumer consumer) {
        if (first != null && second != null)
            consumer.accept(first, second);
    }
    //BiConsumer<? super T, ? super V> consumer
}
