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

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {

        Map<String, Set<String>> map = new TreeMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset))) {

            List<String> words = bufferedReader.lines().
                    map(String::toLowerCase).
                    filter(word -> word.length() >= 3 && word.matches("[а-яё]+")).
                    collect(Collectors.toList());

            for (String word : words) {
                char[] symbolsInWord = word.toLowerCase().toCharArray();
                Arrays.sort(symbolsInWord);
                String key = new String(symbolsInWord);
                if (map.containsKey(key)) {
                    map.get(key).add(word.toLowerCase());
                } else {
                    map.put(key, new TreeSet<>());
                    map.get(key).add(word.toLowerCase());

                }
            }

        } catch (Exception e) {
        }

        return map.values().
                stream().
                filter(s -> s.size() >= 2).
                collect(Collectors.toList());

    }
}
