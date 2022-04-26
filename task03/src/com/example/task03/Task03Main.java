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
        Map<String, TreeSet<String>> result = new TreeMap<>();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            List<String> entries = br.lines()
                    .map(String::toLowerCase)
                    .filter(x -> x.matches("[а-яё]+"))
                    .collect(Collectors.toList());
            for(String s : entries) {
                char[] symbols = s.toCharArray();
                Arrays.sort(symbols);
                String key = new String(symbols);
                result.computeIfAbsent(key, a -> new TreeSet<>()).add(s);
            }
        } catch (IOException ignored) { }

        return result.values()
                .stream()
                .filter(x -> x.stream().allMatch(y -> y.length() >= 3) && x.size() >= 2)
                .collect(Collectors.toList());
    }
}
