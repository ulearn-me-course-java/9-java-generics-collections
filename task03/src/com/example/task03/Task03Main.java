package com.example.task03;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams =
                findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }
    }

    private static int getHach(String s) {
        Map<Character, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            Character c = s.charAt(i);
            if (!isCorrectSymbol(c)) return -1;
            map.put(c, map.containsKey(c) ? map.get(c) + 1 : 1);
        }
        return map.hashCode();
    }

    private static boolean isCorrectSymbol(Character c) {
        return (c >= 'а' && c <= 'я') || (c >= 'А' && c <= 'Я');
    }


    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        Map<Integer, HashSet<String>> map = getIntegerLinkedListMap(new Scanner(inputStream, charset.name()));
        List<Set<String>> result = new ArrayList<>();
        for (HashSet<String> value : map.values()) {
            if(value.size() == 1) continue;
            Set<String> set = new TreeSet<>(value);
            result.add(set);
        }

        result.sort(Comparator.comparing(o -> o.iterator().next()));

        return result;
    }

    private static Map<Integer, HashSet<String>> getIntegerLinkedListMap(Scanner input) {
        Map<Integer, HashSet<String>> map = new HashMap<>();
        while (input.hasNext()) {
            String word = input.next().toLowerCase();
            int hash;
            if (word.length() < 3 || (hash = getHach(word)) == -1)
                continue;
            if (!map.containsKey(hash)) map.put(hash, new HashSet<>());
            map.get(hash).add(word);
        }
        return map;
    }
}
