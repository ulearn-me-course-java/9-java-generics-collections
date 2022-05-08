package com.example.task03;

import sun.reflect.generics.tree.Tree;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.*;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.Scanner;
import java.util.Set;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(Files.newInputStream(Paths.get("task03/resources/singular.txt")), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        TreeSet<String> words = getWords(inputStream, charset);

        Map<String, TreeSet<String>> sortedWords = new TreeMap<>();
        for(String currentWord : words){
            char[] chars = currentWord.toCharArray();
            Arrays.sort(chars);
            if(sortedWords.containsKey(new String(chars))){
                TreeSet<String> wordsWithCurrentKey = sortedWords.get(new String(chars));
                wordsWithCurrentKey.add(currentWord);
                sortedWords.put(new String(chars), wordsWithCurrentKey);
            }
            else{
                TreeSet<String> s = new TreeSet<>();
                s.add(currentWord);
                sortedWords.put(new String(chars), s);
            }
        }

        Collection<TreeSet<String>> coll = sortedWords.values();
        List<Set<String>> result = new ArrayList<>();
        for(Set<String> item : coll){
            if (item.size() > 1){
                result.add(item);
            }
        }
        return result;
    }

    private static TreeSet<String> getWords(InputStream inputStream, Charset charset) {
        Scanner scanner = new Scanner(inputStream, charset.name()).useDelimiter("\n");
        TreeSet<String> words = new TreeSet<>();
        try {
            while (scanner.hasNext()) {
                String word = scanner.nextLine().toLowerCase(Locale.ROOT);
                if (word.length() > 2) {
                    boolean flag = true;
                    for (char chars : word.toCharArray()) {
                        if (chars < 'а' || chars > 'я') {
                            flag = false;
                            break;
                        }
                    }
                    if (flag)
                        words.add(word);
                }
            }
            scanner.close();
        }catch (Exception e) {
            throw new RuntimeException();
        }
        return words;
    }
}
