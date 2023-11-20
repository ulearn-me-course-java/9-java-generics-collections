package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        TreeMap<String, SortedSet<String>> map = doAnagram(inputStream, charset);
        List<Set<String>> resultSet = doCorrectFormat(map);
        return resultSet;
    }

    private static TreeMap<String, SortedSet<String>> doAnagram(InputStream inputStream, Charset charset){
        TreeMap<String, SortedSet<String>> map = new TreeMap<String, SortedSet<String>>();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            String currentLine;
            while ((currentLine = reader.readLine())!= null){
                String word = currentLine.toLowerCase();
                char[] chars = word.toCharArray();
                Arrays.sort(chars);
                String wordSort = new String(chars);
                if(wordSort.length() < 3 || !wordSort.matches("[а-яё]+")){
                    continue;
                }
                addInMap(map, wordSort, word);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return map;
    }

    private static void addInMap(TreeMap<String, SortedSet<String>> map, String wordSort, String word){
        if(map.containsKey(wordSort)) {
            SortedSet<String> set = map.get(wordSort);
            set.add(word);
        }
        else {
            SortedSet<String> set = new TreeSet<>();
            set.add(word);
            map.put(wordSort, set);
        }
    }

    private static List<Set<String>> doCorrectFormat(TreeMap<String, SortedSet<String>> map) {
        TreeMap<String, Set<String>> resultMap = new TreeMap<String, Set<String>>();
        for(SortedSet<String> set : map.values()) {
            if(set.size() > 1) {
                resultMap.put(set.first(), set);
            }
        }
        List<Set<String>> resultSet = new ArrayList<>();
        for (Set<String> set: resultMap.values()) {
            resultSet.add(set);
        }
        return resultSet;
    }
}
