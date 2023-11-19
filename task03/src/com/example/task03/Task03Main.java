package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }
    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        Map<String, Set<String>> anagrams = new TreeMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            bufferedReader.lines() // получить все строки из входящего потока
                    .map(String::toLowerCase) //перегнать их в нижний регистр
                    .filter(word -> word.length() >= 3 && word.matches("[а-яё]*")) // проверка на длинну и русские буквы
                    .forEach(word -> {
                        char[] chars = word.toCharArray();
                        Arrays.sort(chars);
                        String sortedWord = new String(chars); // получить word с буквами в алфавитном порядке
                        anagrams.computeIfAbsent(sortedWord, s -> new TreeSet<>()).add(word);
                    });
        } catch (IOException ignored) {}
        return anagrams.values()
                .stream() //преобразуем в поток для следующих действий
                .filter(set -> set.size() >= 2) // берём только наборы из 2 слов или больше
                .collect(Collectors.toList()); // преобразуем поток в список
    }
}
