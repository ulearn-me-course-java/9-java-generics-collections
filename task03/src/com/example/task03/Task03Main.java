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
        List<Set<String>> result = new ArrayList<>();
        Map<String, Set<String>> anagrams = new TreeMap<>();
        String wordsStr = new BufferedReader(new InputStreamReader(inputStream, charset))
                .lines().collect(Collectors.joining("\n"));
        String[] words = wordsStr.split("\\s");
        for (String word : words
        ) {
            word = word.toLowerCase();
            char[] checkKey = word.toCharArray();
            Arrays.sort(checkKey);
            String keyStr = new String(checkKey);
            if (!anagrams.containsKey(keyStr)) {
                if (checkWord(word)) {
                    anagrams.put(keyStr, new HashSet<String>());
                    anagrams.get(keyStr).add(word);
                }
            } else {
                if (checkWord(word)) {
                    anagrams.get(keyStr).add(word);
                }
            }
        }
        result = anagrams
                .values()
                .stream()
                .filter(x -> x.size() > 1)
                .map(p -> p.stream()
                        .sorted()
                        .collect(Collectors.toCollection(LinkedHashSet::new)))
                .collect(Collectors.toList());
        return result;
    }

    public static boolean checkWord(String word) {
        return word.length() > 3 && word.matches("^[а-я]+$");
    }
}
