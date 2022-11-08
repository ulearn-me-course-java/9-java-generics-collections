package com.example.task03;


import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;
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

        Map<String, Set<String>> map = new TreeMap<>();
        List<Set<String>> result = new ArrayList<>();

        try (BufferedReader bfReader = new BufferedReader(new InputStreamReader(inputStream, charset))){
            List<String> words = bfReader.lines().collect(Collectors.toList());

            for (String word : words ){
                if (word.length() >= 3 && word.toLowerCase().matches("[а-яё]+")){
                    char[] b = word.toLowerCase().toCharArray();
                    Arrays.sort(b);
                    String key = new String(b);
                    if (map.containsKey(key)){
                        map.get(key).add(word.toLowerCase());
                    }else{
                        map.put(key, new TreeSet<>());
                        map.get(key).add(word.toLowerCase());
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
}
