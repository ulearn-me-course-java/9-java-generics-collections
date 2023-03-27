package com.example.task03;

import jdk.nashorn.internal.runtime.regexp.joni.Matcher;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Pattern;
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
        List<Set<String>> result = new ArrayList<>();
        List<String> words = new ArrayList<>();
        //  ArrayList<String> rWords = new ArrayList<>();
        Pattern pattern = Pattern.compile("[а-яА-ЯёЁ]*");

        try (BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            words = bufferedReader.lines().collect(Collectors.toList());
            for (String word : words) {
                if (word.length() >= 3 && pattern.matcher(word).matches() == true) {
                    //   rWords.add(word.toLowerCase());
                    char[] b = word.toLowerCase().toCharArray();
                    Arrays.sort(b);
                    String key = new String(b);
                    if (res.containsKey(key) == true) {
                        res.get(key).add(word.toLowerCase());
                    } else {
                        res.put(key, new TreeSet<>());
                        res.get(key).add(word.toLowerCase());
                    }
                }
            }
            for (String key : res.keySet()) {
                if (res.get(key).size() >= 2) {
                    result.add(res.get(key));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }
}