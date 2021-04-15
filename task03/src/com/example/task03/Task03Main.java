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
        String word;

        List<String> words = new ArrayList<>();
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            while ((word = reader.readLine()) != null) {
                if (checkWordCorrectness(word))
                    words.add(word.toLowerCase());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        Collections.sort(words);
        Map<String, List<String>> map = findAnagrams(words);
        List<Set<String>> result = new ArrayList<>();
        for (Map.Entry<String, List<String>> pair : map.entrySet()) {
            List<String> list = pair.getValue();
            Set<String> set = new TreeSet<>(list);
            if (list.size() > 1 && set.size() > 1) {
                result.add(set);
            }
        }
        return result;
    }

    public static boolean checkWordCorrectness(String word) {
        if (word.length() < 3)
            return false;
        for (int i = 0; i < word.length(); i++) {
            if (!(Character.UnicodeBlock.of(word.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)))
                return false;
        }
        return true;
    }

    public static Map<String, List<String>> findAnagrams(List<String> words) {
        Map<String, List<String>> map = new TreeMap<>();
        for (String word : words) {
            char[] letters = word.toCharArray();
            Arrays.sort(letters);
            String checkWord = new String(letters);
            if (map.containsKey(checkWord)) {
                map.get(checkWord).add(word);
            } else {
                List<String> list = new ArrayList<>();
                list.add(word);
                map.put(checkWord, list);

            }
        }
        return map;
    }
}
