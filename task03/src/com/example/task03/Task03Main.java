package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

import static java.util.Arrays.sort;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        List<String> words = ReadFile(inputStream, charset);
        List<Set<String>> result = new ArrayList<>();

        for (int i = 0; i < words.size(); i++) {
            Set<String> set = new TreeSet<>();
            for(int j = i + 1; j < words.size(); j++){
                if(IsAnagram(words.get(i), words.get(j))){
                    set.add(words.get(i));
                    set.add(words.get(j));
                    words.remove(j);
                    j--;
                }
            }
            if(!set.isEmpty()){
                result.add(set);
            }
        }

        return result;
    }

    private static List<String> ReadFile(InputStream inputStream, Charset charset) {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            String line = reader.readLine();
            while (line != null) {
                if(IsCorrectWord(line.toLowerCase())){
                    words.add(line.toLowerCase());
                }
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(words);
        return words;
    }

    private static boolean IsAnagram (String first,String second) {
        if (first.length() != second.length()) {
            return false;
        }
        return SortWord(first).equals(SortWord(second)) && !first.equals(second);
    }

    private static String SortWord(String s) {
        char[] content = s.toCharArray();
        sort(content);
        return new String(content);
    }

    private static boolean IsCorrectWord(String word){
        for (char letter:word.toCharArray()) {
            if(letter < 'а' || letter > 'я'){
                return false;
            }
        }
        return word.length() > 2;
    }
}