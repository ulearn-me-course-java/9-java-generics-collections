package com.example.task03;

import javafx.collections.transformation.SortedList;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Pattern;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        TreeSet<String> anagram;

        Set<TreeSet<String>> result = new TreeSet<>(Comparator.comparing(TreeSet::first));
        List<String> inputList = new ArrayList<>();
        List<String> wordsPassed = new ArrayList<>();

        new BufferedReader(new InputStreamReader(inputStream, charset)).lines().forEach(inputList::add);

        for (int i = 0; i < inputList.size(); i++) {
            String str1 = inputList.get(i);

            if (str1.length() < 3 || wordsPassed.contains(str1) ||
                    !Pattern.matches(".*\\p{InCyrillic}.*", str1) || str1.contains("-")) {
                continue;
            }

            char[] ch1, ch2;
            anagram = new TreeSet<>();

            for (int j = i + 1; j < inputList.size(); j++) {
                String str2 = inputList.get(j);

                str1 = str1.toLowerCase();
                str2 = str2.toLowerCase();
                if (str1.equals(str2)){
                    break;
                }
                ch1 = str1.toCharArray();
                ch2 = str2.toCharArray();
                Arrays.sort(ch1);
                Arrays.sort(ch2);
                if (String.valueOf(ch1).equals(String.valueOf(ch2))) {
                    wordsPassed.add(str2);
                    anagram.add(str2);
                }

            }
            if (!anagram.isEmpty()) {
                anagram.add(str1);
                result.add(anagram);
            }
            wordsPassed.add(str1);

        }
        return new ArrayList<>(result);
    }
}
