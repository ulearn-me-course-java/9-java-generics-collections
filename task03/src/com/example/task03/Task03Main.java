package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        Map<String, Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> entry : anagrams.values()) {
            Iterator<String> s = entry.iterator();
            while (s.hasNext()) {
                System.out.println(s.next());
            }
            System.out.println();
        }

    }

    public static Map<String, Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        Map<String, Set<String>> resultMap = new HashMap();

        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            List<String> words = br.lines()
                    .map(String::toLowerCase)
                    .filter(x -> x.length() > 2)
                    .filter(x -> x.matches("[а-яё]+"))
                    .collect(Collectors.toList());
            for (String word: words) {
                char[] letters = word.toCharArray();
                Arrays.sort(letters);
                String key = new String(letters);
                resultMap.computeIfAbsent(key, set -> new TreeSet<>()).add(word);
            }
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }

        resultMap.values().removeIf(value -> value.size() < 2);

        return resultMap;
    }
}
