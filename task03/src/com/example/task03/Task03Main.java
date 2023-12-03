package com.example.task03;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.io.*;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        Map<String, Set<String>> map = new TreeMap<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            Stream<String> stream = br.lines();
            List<String> list = stream
                    .map(x -> x.toLowerCase())
                    .filter(x -> x.length() >= 3)
                    .filter(x -> x.matches("[а-яё]+"))
                    .sorted()
                    .collect(Collectors.toList());
            for(String elem : list) {
                char[] symbols = elem.toCharArray();
                Arrays.sort(symbols);
                String key = new String(symbols);
                map.computeIfAbsent(key, x -> new TreeSet<>()).add(elem);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return map.values()
                .stream().filter(x -> x.size() >= 2)
                .collect(Collectors.toList());
    }
}
