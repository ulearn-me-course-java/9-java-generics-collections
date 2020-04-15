package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class Task03Main {

    public static final int start = 'а';

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }
    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset){
        Set<String> rightWords = null;
        try {
            rightWords = parseRightWords(inputStream, charset);
        } catch (IOException e) {
            throw new RuntimeException("Can't parse file : " + e.getMessage());
        }

        Map<Word, Set<String>> map = new HashMap<>();

        rightWords.forEach((w) -> {
            Word curWord = new Word(w);
            Set<String> words = map.get(curWord);
            if (words == null) {
                words = new TreeSet<>();
                map.put(curWord, words);
            }
            words.add(curWord.word);
        });

        List<Set<String>> collect = map.values().stream().filter(el -> el.size() > 1).sorted((f, s) -> {
            TreeSet<String> first = (TreeSet) f;
            TreeSet<String> second = (TreeSet) s;
            return first.first().compareTo(second.first());
        }).collect(Collectors.toList());

        return collect;
    }

    private static Set<String> parseRightWords(InputStream inputStream, Charset charset) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset));
        Set<String> set = new HashSet<>();

        String curLine;

        while ((curLine = bufferedReader.readLine()) != null) {
            if (isRightWord(curLine)) {
                set.add(curLine.toLowerCase());
            }
        }
        return set;
    }

    private static boolean isRightWord(String word) {
        return isRussian(word) && isRightLength(word);
    }

    private static boolean isRussian(String word) {
        return word.matches("[а-я]+");
    }

    private static boolean isRightLength(String word) {
        return word.length() >= 3;
    }
}
