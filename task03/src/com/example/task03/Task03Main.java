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
        Map<String, Set<String>> anagrams = new TreeMap<>();
        List<Set<String>> list = new ArrayList<>();
        try (BufferedReader bufferReader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            List<String> words = bufferReader.lines().collect(Collectors.toList());

            for(String word : words){

                if (word.toLowerCase().matches("[а-яё]+") && word.length() > 2){

                    char[] chars = word.toLowerCase().toCharArray();
                    Arrays.sort(chars);
                    String key = new String(chars);
                    if (anagrams.containsKey(key)){
                        anagrams.get(key).add(word.toLowerCase());
                    }
                    else{
                        TreeSet<String> newWord = new TreeSet<>();
                        newWord.add(word.toLowerCase());
                        anagrams.put(key, newWord);
                    }
                }
            }
            for (String key: anagrams.keySet()){
                if (anagrams.get(key).size() > 1){
                    list.add(anagrams.get(key));
                }
            }
        }
        catch(Exception e){
            e.getMessage();
        }
        return list;
    }
}
