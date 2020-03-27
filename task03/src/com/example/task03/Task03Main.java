package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.defaultCharset());
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        HashMap<String, HashSet<String>> result = new HashMap<>();
        new BufferedReader(new InputStreamReader(inputStream, charset))
                .lines()
                .map(String::toLowerCase)
                .filter(it -> it.length() >= 3 && it.matches("[а-я]*"))
                .distinct()
                .forEach(it -> {
                    char[] sorted = it.toCharArray();
                    Arrays.sort(sorted);
                    String key = Arrays.toString(sorted);
                    if(result.containsKey(key)){
                        result.get(key).add(it);
                    } else {
                        HashSet<String> list = new HashSet<>();
                        list.add(it);
                        result.put(key, list);
                    }
                });

        return result.values().stream().filter(it -> it.size() > 1).collect(Collectors.toList());
    }
}