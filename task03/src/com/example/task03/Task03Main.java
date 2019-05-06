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

        Set<TreeSet<String>> anagrams = new TreeSet<>(Comparator.comparing(TreeSet::first));
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset));

        List<String> lines = reader.lines().collect(Collectors.toList());
        while (lines.size() != 0) {
            int i = 0;
            TreeSet<String> anagram = new TreeSet<>();
            anagram.add(lines.get(0).toLowerCase());
            for (int j = 1; j < lines.size(); j++) {
                String curr = lines.get(j);
                if (curr.length() >= 3 && curr.toLowerCase().matches("^[а-я]*$")) {
                    if ((curr.length() == anagram.first().length()
                            && sort(anagram.first()).equals(sort(curr.toLowerCase())))) {
                        anagram.add(lines.get(j).toLowerCase());
                        lines.remove(j);
                    }
                } else {
                    lines.remove(j);
                }

            }
            if (anagram.size() >= 2)
                anagrams.add(anagram);
            lines.remove(i);
        }

        return new ArrayList<>(anagrams);
    }

    private static String sort(String str) {
        char[] el = str.toLowerCase().toCharArray();
        Arrays.sort(el);
        return new String(el);
    }
}
