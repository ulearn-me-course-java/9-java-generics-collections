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
        Set<String> allWords = new HashSet<>();

        Scanner reader = new Scanner(new InputStreamReader(inputStream));
        try {
            while (reader.hasNext()) {
                String line = reader.nextLine().toLowerCase(Locale.ROOT);
                if (line.length() >= 3) {
                    boolean add = true;
                    for (char letter : line.toCharArray()) {
                        if (letter < 'а' || letter > 'я') {
                            add = false;
                            break;
                        }
                    }
                    if (add)
                        allWords.add(line);
                }
            }
            reader.close();
        } catch (Exception e) {
            throw new RuntimeException();
        }

        List<Set<String>> result = new ArrayList<>();
        LinkedList<String> allWordsList = new LinkedList<>(allWords);
        while (!allWordsList.isEmpty()) {
            String mainWord = allWordsList.getFirst();
            allWordsList.removeFirst();

            SortedSet<String> set = new TreeSet<>();
            set.add(mainWord);

            Iterator<String> iterator = allWordsList.iterator();
            while (iterator.hasNext()) {
                String word = iterator.next();
                if (isAnagram(mainWord, word)) {
                    set.add(word);
                    iterator.remove();
                }
            }

            if (set.first() != set.last())
                result.add(set);
        }

        result.sort((first, second) ->
                ((SortedSet<String>)first).first().compareTo(((SortedSet<String>)second).first()));

        return result;
    }

    private static boolean isAnagram(String first, String second) {
        if (first.length() != second.length())
            return false;

        int[] letterCounts = new int[32];

        for (char letter : first.toCharArray())
            letterCounts[letter - 'а']++;
        for (char letter : second.toCharArray())
            letterCounts[letter - 'а']--;

        for (int num : letterCounts) {
            if (num != 0)
                return false;
        }
        return true;
    }
}
