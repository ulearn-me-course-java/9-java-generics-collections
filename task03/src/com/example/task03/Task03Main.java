package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("UTF-8"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {

        TreeSet<String> anagram;
        Set<TreeSet<String>> res = new TreeSet<>(Comparator.comparing(TreeSet::first));
        List<String> input = new ArrayList<>();
        List<String> usesWords = new ArrayList<>();

        new BufferedReader(new InputStreamReader(inputStream, charset)).lines().forEach(x -> input.add(x));

        for (int i = 0; i < input.size(); i++) {
            String s1 = input.get(i);

            if (s1.length() <= 3 || usesWords.contains(s1) || !s1.matches("^[а-яА-Я]+$")) { continue; }

            char[] c1, c2;
            anagram = new TreeSet<>();

            for (int j = i + 1; j < input.size(); j++) {
                String s2 = input.get(j);

                s1 = s1.toLowerCase();
                s2 = s2.toLowerCase();

                if(s1.equals(s2)){ break; }

                c1 = s1.toCharArray();
                c2 = s2.toCharArray();

                Arrays.sort(c1);
                Arrays.sort(c2);

                if (String.valueOf(c1).equals(String.valueOf(c2))) {
                    anagram.add(s2);
                    usesWords.add(s2);
                }
            }

            if (!anagram.isEmpty()) {
                anagram.add(s1);
                res.add(anagram);
            }

            usesWords.add(s1);
        }

        return new ArrayList<>(res);
    }
}
