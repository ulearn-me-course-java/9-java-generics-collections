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
        Map<String, Set<String>> anagrams = new LinkedHashMap<>();

        Scanner scanner = new Scanner(inputStream, charset.name());
        while (scanner.hasNext()) {
            String word = scanner.nextLine().toLowerCase();
            if (!isWordCorrect(word)) {
                continue;
            }

            String wordWithSortedChars = sortCharsInWord(word);
            if (!anagrams.containsKey(wordWithSortedChars)) {
                anagrams.put(wordWithSortedChars, new LinkedHashSet<>());
            }
            anagrams.get(wordWithSortedChars).add(word);
        }

        anagrams = filterAnagrams(anagrams);
        anagrams = sortAnagrams(anagrams);

        return new ArrayList<>(anagrams.values());
    }

    private static Map<String, Set<String>> filterAnagrams(Map<String, Set<String>> anagrams) {
        Map<String, Set<String>> filtered = new LinkedHashMap<>();

        for (String key : anagrams.keySet()) {
            if (anagrams.get(key).size() > 1) {
                filtered.put(key, anagrams.get(key));
            }
        }

        return filtered;
    }

    private static Map<String, Set<String>> sortAnagrams(Map<String, Set<String>> anagrams) {
        Map<String, Set<String>> sortedInsideEachSet = new LinkedHashMap<>();
        for (String key : anagrams.keySet()) {
            List<String> list = new ArrayList<>(anagrams.get(key));
            list.sort(Comparator.naturalOrder());
            sortedInsideEachSet.put(list.get(0), new LinkedHashSet<>(list));
        }

        List<String> firstWords = new ArrayList<>(sortedInsideEachSet.keySet());
        firstWords.sort(Comparator.naturalOrder());

        Map<String, Set<String>> sortedByFirstWords = new LinkedHashMap<>();
        for (String key : firstWords) {
            sortedByFirstWords.put(key, sortedInsideEachSet.get(key));
        }

        return sortedByFirstWords;
    }

    private static String sortCharsInWord(String word) {
        char[] arr = word.toCharArray();
        Arrays.sort(arr);
        return Arrays.toString(arr);
    }

    private static boolean isWordCorrect(String word){
        if (word.length() < 3) {
            return false;
        }

        for (char ch : word.toCharArray()) {
            if (!Character.UnicodeBlock.of(ch).equals(Character.UnicodeBlock.CYRILLIC)) {
                return false;
            }
        }

        return true;
    }
}
