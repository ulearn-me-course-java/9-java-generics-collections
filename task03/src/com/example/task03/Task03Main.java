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

        Tests tests = new Tests();
        tests.testExample();
        tests.test();
        tests.testErrors();
        tests.testEmpty();
    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        Map<String, Set<String>> anagrams = new TreeMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            bufferedReader.lines() // получить все строки
                    .map(String::toLowerCase) // в нижний регистр
                    .filter(word -> word.length() >= 3 && word.matches("[а-яё]*")) // фильтр: длина слова >= + русские буквы
                    .forEach(word -> {
                        char[] chars = word.toCharArray();
                        Arrays.sort(chars);
                        String sortedWord = new String(chars); // получить word с буквами в алфавитном порядке
                        anagrams.computeIfAbsent(sortedWord, k -> new TreeSet<>()).add(word);
                    });
        } catch (IOException ignored) {
        }

        return anagrams.values()
                .stream()
                .filter(set -> set.size() >= 2) // получить наборы минимум из 2х слов
                .collect(Collectors.toList()); // преобразует Stream в List
    }
}
