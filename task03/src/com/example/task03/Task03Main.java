package com.example.task03;

import java.io.*;
import java.util.*;
import java.nio.charset.Charset;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static boolean filter(String s) {
        if (s.length() <= 3)
            return false;

        for (char c: s.toCharArray()) {
            if ((c < 'а') || (c > 'я')) {
                return false;
            }
        }
        return true;
    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        Set<TreeSet<String>> result = new TreeSet<TreeSet<String>>(Comparator.comparing(TreeSet::first));
        List<String> words = new ArrayList<String>();
        char[][] wordsChars;

        new BufferedReader(new InputStreamReader(inputStream, charset))
                .lines()
                .map(String::toLowerCase)
                .distinct()
                .filter(Task03Main::filter)
                .forEach(words::add);

        wordsChars = new char[words.size()][];
        for (int i = 0; i < wordsChars.length; i++) {
            wordsChars[i] = words.get(i).toCharArray();
            Arrays.sort(wordsChars[i]);
        }

        for (int i = 0; i < words.size(); i++) {
            TreeSet<String> anagrams = new TreeSet<String>();

            for (int j = i + 1; j < words.size(); j++) {
                if (Arrays.equals(wordsChars[i], wordsChars[j])) {
                    anagrams.add(words.get(j));
                }
            }

            if (!anagrams.isEmpty()) {
                anagrams.add(words.get(i));
                result.add(anagrams);
            }
        }

        return new ArrayList<Set<String>>(result);
    }
}
