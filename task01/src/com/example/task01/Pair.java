package com.example.task01;

import java.util.Objects;
import java.util.function.BiConsumer;

    public class Pair<T, U> {
        private final T valueT;
        private final U valueU;

        public static <T, U> Pair<T, U> of (T param1, U param2){
            return new Pair(param1, param2);
        }

        public void ifPresent(BiConsumer consumer){
            if (valueT != null && valueU != null) consumer.accept(valueT, valueU);
        }

        public T getFirst(){
            return valueT;
        }

        public U getSecond(){
            return valueU;
        }

        @Override
        public int hashCode(){
            return Objects.hash(valueT, valueU);
        }

        @Override
        public boolean equals(Object obj){
            if (this == obj) return true;
            if (!(obj instanceof Pair)) return false;

            Pair p = (Pair) obj;
            return valueT.equals(p.valueT) && valueU.equals(p.valueU);
        }

        private Pair(T valueT, U uValue) {
            this.valueT = valueT;
            this.valueU = uValue;
        }
    }