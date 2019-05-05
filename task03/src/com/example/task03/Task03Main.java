package com.example.task03;

import java.nio.charset.Charset;
import java.util.LinkedList;
import java.util.List;
import java.util.*;
import java.io.*;
import java.util.stream.Collectors;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }
    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        Map<String, Set<String>> map = new TreeMap<>();// красно-черное дерево
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            reader
                    .lines()
                    .map(String::toLowerCase)//промежуточный поток, переведенный внижний регистр
                    .filter(str -> str.length() > 2 && str.matches("[а-я]*"))//только русские буквы и больше 3 символов
                    .forEach(str -> {
                        char[] ch = str.toCharArray();
                        Arrays.sort(ch);
                        String sortedLetters = new String(ch);//отсортированные буквы в слове
                        map.computeIfAbsent(sortedLetters, k -> new TreeSet<>()).add(str);//создать ключ, если такого нет
                    });

        } catch (IOException ex) {
            ex.printStackTrace();
        }
        //каждый набор должен состоять минимум из 2 слов
        return map.values().stream()
                .filter(setList -> setList.size() > 1)
                .collect(Collectors.toList());
    }
}
