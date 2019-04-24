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
            bufferedReader
                    .lines()
                    .map(String::toLowerCase)
                    .filter(i -> i.length() >= 3 && i.matches("^[а-я]*$"))
                    .forEach(i -> {
                        String sortedString = sortedString(i);
                        map.computeIfAbsent(sortedString, k -> new TreeSet<>()).add(i);
                        map.computeIfPresent(sortedString, (k, v) -> v).add(i);
                    });
        } catch (IOException e) {
            e.printStackTrace();
        }
        return map.values().stream()
                .filter(i -> i.size() > 1)
                .collect(Collectors.toList());
    }

    private static String sortedString(String value) {
        char[] el = value.toCharArray();
        Arrays.sort(el);
        return String.valueOf(el);
    }

}
