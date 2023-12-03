package com.example.task01;


import java.util.Objects;
import java.util.function.BiConsumer;

public class Pair <K,V> {
    // TODO напишите реализацию
    private K key = null;
    private V value = null;

    public K getFirst()
    {
        return key;
    }
    public V getSecond()
    {
        return value;
    }

    public Boolean equals(Pair<K,V> pair)
    {
        return pair==this || ((pair.getFirst() == this.getFirst()) && (pair.getSecond() == this.getSecond()));
    }
    public int hashCode()
    {
        return Objects.hash(key,value);
    }

    public static <K,V> Pair<K,V> of(K Key, V Value)
    {
        return new Pair<K,V>(Key,Value);
    }

    public void ifPresent(BiConsumer<? super K,? super V> biConsumer)
    {
        if(key!=null && value!=null)
        {
            biConsumer.accept(key, value);
        }
    }

    private Pair(K Key,V Value)
    {
        this.key = Key;
        this.value = Value;
    }
}
