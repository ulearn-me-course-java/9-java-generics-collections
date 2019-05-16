package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));

        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    // :: - указатель на метод (method refernce) https://toster.ru/q/578228
    // Лямбда-выражение - анонимная функция без объявления, только (параметры) -> (тело)

    // https://habr.com/ru/company/luxoft/blog/270383/

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        Map<String, Set<String>> map = new TreeMap<>(); // Красно-черное дерево

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            reader
                .lines() // Считывание строкк с буфера
                .map(String::toLowerCase) // Промежуточный поток, переведенный внижний регистр
                // Преобразует каждый элемент стрима по какому-то правилу, возвращает поток
                .filter(str ->
                    str.length() >= 3 && str.replace("ё", "е").replace("Ё", "Е")
                    .matches("^[а-яА-Я]*$") // Применяется к строке, которую обработали replace
                )
                // Фильтр - возвращает поток, состоящий из элементов потока, которые соответствую предикату
                // Просто фильтрует записи, удовлетворяющие некоторому условию
                // Предикат - функция, принимающая один аргумент, соответствие некоторой характеристике (возвращает true/false)
                // Например, Predicate<String> strIsEmpty = String::isEmpty;
                .distinct() // Без повторяющихся слов
                .forEach(str -> {
                    char[] ch = str.toCharArray(); // Приводим строку в массив символов
                    Arrays.sort(ch); // Сортируем
                    String sortedLetters = new String(ch); // Отсортированная строка
                    map.computeIfAbsent(sortedLetters, k -> new TreeSet<>())
                    // Принимает ключ и функцию расчёта значения по ключу. В данном случае, если ключ отсутствует
                    // То будет создано пустое множество, в которое в дальнейшем будут добавляться анаграммы
                    // Если же ключ уже существует, он вернёт значение - дерево TreeSet
                    .add(str); // Добавление значения (строки) в обычном виде в дерево TreeSet

                    // TreeSet используется, т.к. в этом дереве все объёкты хранятся в отсортированном виде, по возрастанию
                });
                // forEach - применение функции к каждому объекту потока, порядок при выполнении не гарантируется

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        // В качестве ключей в map используются обработанные слова, которые могут быть анаграммами.
        // Например, мы встретили слово нос, отсортировали его посимвольно, добавили в качестве ключа в map
        // А в качестве значения получается дерево TreeSet, которое содержит обычные слова, без обработки
        // Если мы снова встречаем слово, которое при обработке совпадает с ключом, то
        // Дерево увеличивается, при этом сохраняет отсортированный порядок

        // Каждый набор должен состоять минимум из 2 слов
        return map.values() // Возвращает представление Map в качестве множества значений
                .stream() // Возвращает поток с множеством значений, который мы ниже обрабатываем
                .filter(list -> list.size() >= 2) // Выбираем только те деревья, у которых элементов >= 2
                .collect(Collectors.toList());
                // Так как мы модифицировали поток данных (Stream), то он и вернул бы нам поток
                // Чтобы преобразовать поток в коллекцию, используется метод collect(),
                // Который принимает функцию преобразования к конкретной коллекции (в данном случае к списку)
    }
}
