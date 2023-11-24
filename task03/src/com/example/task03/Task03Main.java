package com.example.task03;

import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task03Main {

    public static void main(String[] args) {
        checkSymbols("qwerty");
    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        Scanner scanner = new Scanner(inputStream);
        Map<String, SortedSet<String>> wordsAndCharacters = new HashMap<>();
        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().toLowerCase();
            if (line.length() < 3 || !checkSymbols(line)) {
                continue;
            }
            char[] chars = line.toCharArray();
            Arrays.sort(chars);
            String str = String.valueOf(chars);
            if (wordsAndCharacters.containsKey(str)) {
                wordsAndCharacters.get(str).add(line);
            } else {
                SortedSet<String> set = new TreeSet<>();
                set.add(line);
                wordsAndCharacters.put(str, set);
            }
        }
        Collection<SortedSet<String>> values = wordsAndCharacters.values();
        Stream<SortedSet<String>> stream = values.stream().filter(set -> set.size() > 1);
        List<SortedSet<String>> temp = stream.collect(Collectors.toList());
        sort(temp);
        return new ArrayList<>(temp);
    }

    private static void sort(List<SortedSet<String>> setList) {
        for (int i = 0; i < setList.size(); i++) {
            for (int j = 0; j < setList.size() - 1; j++) {
                char c1 = setList.get(j).first().toCharArray()[0];
                char c2 = setList.get(j + 1).first().toCharArray()[0];
                if (c1 > c2) {
                    SortedSet<String> temp = setList.get(j);
                    setList.set(j, setList.get(j + 1));
                    setList.set(j + 1, temp);
                }
            }
        }
    }

    private static boolean checkSymbols(String string) {
        for (char c : string.toCharArray()) {
            if (c < 1072 || c > 1103) {
                return false;
            }
        }
        return true;
    }
}