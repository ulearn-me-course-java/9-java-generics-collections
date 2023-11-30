package com.example.task03;

import java.io.*;
import java.lang.reflect.Array;
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
        Map<String, Set<String>> treeMap = new TreeMap<>();

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset))){
            List<String> inputs = bufferedReader.lines()
                    .map(String::toLowerCase)
                    .filter(x -> x.matches("[а-я]+"))
                    .filter(x -> x.length() >= 3)
                    .collect(Collectors.toList());
            for (String input : inputs) {
                char[] chars = input.toCharArray();
                Arrays.sort(chars);
                String key = new String(chars);
                treeMap.computeIfAbsent(key, set -> new TreeSet<>()).add(input);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return treeMap.values()
                .stream()
                .filter(set -> set.size() >= 2)
                .collect(Collectors.toList());
    }
}
