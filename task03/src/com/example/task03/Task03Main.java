package com.example.task03;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task03Main {

    public static void main(String[] args) throws IOException {
        List<Set<String>> anagrams = findAnagrams(
                new FileInputStream("task03/resources/singular.txt"),
                Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }
    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        Scanner scanner = new Scanner(inputStream);
        Map<String, SortedSet<String>> wordsAndSymbols = new HashMap<>();

        while (scanner.hasNextLine()) {
            String line = scanner.nextLine().toLowerCase();

            if (line.length() < 3 || !symbolsAreRussian(line)) {
                continue;
            }

            char[] symbols = line.toCharArray();
            Arrays.sort(symbols);
            String str = String.valueOf(symbols);

            if (wordsAndSymbols.containsKey(str)) {
                wordsAndSymbols.get(str).add(line);
            } else {
                SortedSet<String> set = new TreeSet<>();
                set.add(line);
                wordsAndSymbols.put(str, set);
            }
        }

        Collection<SortedSet<String>> values = wordsAndSymbols.values();
        Stream<SortedSet<String>> stream = values.stream().filter(set -> set.size() > 1);
        List<SortedSet<String>> temp = stream.collect(Collectors.toList());

        sort(temp);

        return new ArrayList<>(temp);
    }

    private static boolean symbolsAreRussian(String string) {
        for (char sym : string.toCharArray()) {
            if (sym < 1072 || sym > 1103) {
                return false;
            }
        }

        return true;
    }

    private static void sort(List<SortedSet<String>> list) {
        for (int i = 0; i < list.size(); i++) {
            for (int j = 0; j < list.size() - 1; j++) {
                char c1 = list.get(j).first().toCharArray()[0];
                char c2 = list.get(j + 1).first().toCharArray()[0];

                if (c1 > c2) {
                    SortedSet<String> temp = list.get(j);
                    list.set(j, list.get(j + 1));
                    list.set(j + 1, temp);
                }
            }
        }
    }
}
