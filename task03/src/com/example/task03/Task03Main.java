package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class Task03Main {

    public static void main(String[] args) throws IOException {
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }
    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        Map<String, Set<String>> map = new TreeMap<>();
        List<Set<String>> result = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))){
            List<String> words = reader.lines().collect(Collectors.toList());

            for (String word : words ){
                if (word.length() >= 3){
                    String lowerWord = word.toLowerCase();
                    if(containsOnlyRussianLetters(lowerWord)) {
                        String sortedWord = sortWord(lowerWord);
                        if (map.containsKey(sortedWord)){
                            map.get(sortedWord).add(lowerWord);
                        }else{
                            map.put(sortedWord, new TreeSet<>());
                            map.get(sortedWord).add(lowerWord);
                        }
                    }
                }
            }

            for (String key : map.keySet()){
                if(map.get(key).size() >= 2){
                    result.add(map.get(key));
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }

        return result;
    }

    public static String sortWord(String word) {
        char[] chars = word.toCharArray();
        Arrays.sort(chars);
        return new String(chars);
    }

    public static boolean containsOnlyRussianLetters(String word) {
        return word.matches("[а-яё]+");
    }
}