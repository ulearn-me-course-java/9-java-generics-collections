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
        Map<String, Set<String>> anagrams = new TreeMap<>();
        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            bufferedReader.lines().map(String::toLowerCase).filter(x -> x.length() >= 3 && x.matches("[а-яё]*")).forEach(x -> {
                char[] wordInChar = x.toCharArray();
                Arrays.sort(wordInChar);
                String sortedWord = new String(wordInChar);
                anagrams.computeIfAbsent(sortedWord, k -> new TreeSet<>()).add(x);
            });
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return anagrams.values().stream().filter(x -> x.size() >= 2).collect(Collectors.toList());
    }
}
