package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

import static java.util.Map.Entry.comparingByValue;
import static jdk.nashorn.internal.objects.NativeArray.forEach;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }
        System.out.println(anagrams.size());
    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        Map<String, SortedSet<String>> anagrams = new TreeMap<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))){
            reader.lines()
                    .map(String::toLowerCase)
                    .filter(x -> x.length() >= 3 && x.matches("[а-яё]*"))
                    .forEach(x -> {
                        char[] chars = x.toCharArray();
                        Arrays.sort(chars);
                        String word = new String(chars);
                        anagrams.computeIfAbsent(word, y -> new TreeSet<>()).add(x);
                    });

        } catch (IOException e) {
            e.printStackTrace();
        }

        return anagrams
                .values()
                .stream()
                .filter(x -> x.size() >= 2)
                .collect(Collectors.toList());
    }
}
