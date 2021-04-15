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
        try {
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset));
            bufferedReader.lines()
                    .map(String::toLowerCase)
                    .filter(x -> x.matches("[а-яё]+") && x.length() >= 3)
                    .forEach(x -> {
                        char[] letters = x.toCharArray();
                        Arrays.sort(letters);
                        String key = new String(letters);
                        anagrams.computeIfAbsent(key, k -> new TreeSet<>()).add(x);
                    });
        }
        catch (Exception exception) {
            exception.printStackTrace();
        }
        return anagrams.values()
                .stream()
                .filter(x -> x.size() >= 2)
                .collect(Collectors.toList());
    }
}
