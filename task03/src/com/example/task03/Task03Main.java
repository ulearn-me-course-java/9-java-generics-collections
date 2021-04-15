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
        Set<String> words = new TreeSet<>();
        String cur;
        List<Set<String>> res = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset))){
            res = br
                    .lines()
                    .filter(w -> w.length() > 3 && w.matches("^[а-яА-Я]+$"))
                    .collect(Collectors.groupingBy((String x) -> {
                        char[] letters = x.toLowerCase(Locale.ROOT).toCharArray();
                        Arrays.sort(letters);
                        return new String(letters);
                    }, Collectors.mapping(x ->x.toLowerCase(Locale.ROOT), Collectors.toCollection(() ->new TreeSet<String>()))))
                    .values()
                    .stream()
                    .filter(g -> g.size() > 1).map((TreeSet<String> x) -> {
                        x.stream().sorted(Comparator.comparing((String w) -> w));
                        return x;
                    }).sorted(Comparator.comparing(s -> s.first()))
                    .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return res;
    }

}
