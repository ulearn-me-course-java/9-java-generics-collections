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

        Map<String, Set<String>> res = new TreeMap<>();

        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            List<String> lines = bufferedReader.lines()
                    .map(x -> x.toLowerCase())
                    .filter(x -> x.matches("[а-я]+"))
                    .filter(x -> x.length() >= 3)
                    .collect(Collectors.toList());
            for(String line : lines) {
                char[] symbols = line.toCharArray();
                Arrays.sort(symbols);
                String key = new String(symbols);
                res.computeIfAbsent(key, x -> new TreeSet<>()).add(line);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return res.values()
                .stream()
                .filter(x -> x.size() >= 2)
                .collect(Collectors.toList());
    }
}