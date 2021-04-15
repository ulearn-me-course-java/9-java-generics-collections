package com.example.task03;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        Map<String, Set<String>> result = new HashMap<>();
        Scanner scanner = new Scanner(inputStream, charset.name());

        while (scanner.hasNext()) {
            String word = scanner.nextLine().toLowerCase();
            if (!checkCorrectness(word))
                continue;
            String key = getKey(word);
            if (!result.containsKey(key))
                result.put(key, new LinkedHashSet<>());
            result.get(key).add(word);
        }
        result = removeInvalidSets(result);
        result = sort(result);
        return new ArrayList<>(result.values());
    }

    private static String getKey(String token) {
        char[] arr = token.toCharArray();
        Arrays.sort(arr);
        return Arrays.toString(arr);
    }

    private static boolean checkCorrectness(String token){
        for (char c : token.toCharArray())
            if (!Character.UnicodeBlock.of(c).equals(Character.UnicodeBlock.CYRILLIC)) return false;
        return token.length() > 2;
    }

    private static Map<String, Set<String>> removeInvalidSets(Map<String, Set<String>> sets) {
        Map<String, Set<String>> result = new HashMap<>();
        for (String key : sets.keySet())
            if (sets.get(key).size() > 1)
                result.put(key, sets.get(key));
        return result;
    }

    private static Map<String, Set<String>> sort(Map<String, Set<String>> sets) {
        Map<String, Set<String>> innerSort = new HashMap<>();
        for (String key : sets.keySet()) {
            List<String> list = new ArrayList<>(sets.get(key));
            list.sort(Comparator.naturalOrder());
            innerSort.put(list.get(0), new LinkedHashSet<>(list));
        }
        List<String> keys = new ArrayList<>(innerSort.keySet());
        keys.sort(Comparator.naturalOrder());
        Map<String, Set<String>> result = new LinkedHashMap<>();
        for (String key : keys)
            result.put(key, innerSort.get(key));
        return result;
    }
}
