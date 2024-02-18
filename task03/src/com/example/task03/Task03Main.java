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
        Set<String> words = getWords(inputStream, charset);
        Map<String, Set<String>> map = createMap(words);
        return map.values().stream().
                filter(s -> s.size() >= 2).
                collect(Collectors.toList());
    }
    private static Map<String, Set<String>> createMap(Set<String> words) {
        Map<String, Set<String>> map = new LinkedHashMap<>();
        for (String word : words) {
            char[] arr = word.toCharArray();
            Arrays.sort(arr);
            String key = Arrays.toString(arr);
            if (map.containsKey(key)) {
                map.get(key).add(word);
            } else {
                map.put(key, new TreeSet<>());
                map.get(key).add(word);
            }
        }
        return map;
    }
    private static Set<String> getWords(InputStream inputStream, Charset charset) {
        SortedSet<String> list = new TreeSet<>();
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.toLowerCase();
                if(line.length() >= 3 && line.matches("[а-яё]+")) {
                    list.add(line);
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
        return list;
    }
}