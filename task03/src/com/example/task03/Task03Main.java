package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"),
                                                  Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            String pattern = new String("^[а-яА-Я]+$".getBytes(charset));

            return reader.lines()
                         .map(String::toLowerCase)
                         .distinct()
                         .filter(p -> p.length() > 2 && p.matches(pattern))
                         .collect(Collectors.groupingBy(Task03Main::groupByCharacters))
                         .values()
                         .stream()
                         .filter(p -> p.size() > 1)
                         .map(Task03Main::collectToSet)
                         .sorted(Comparator.comparing(o -> String.join("", o)))
                         .collect(Collectors.toList());

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private static Set<String> collectToSet(List<String> stringList) {
        return stringList.stream()
                         .sorted()
                         .collect(Collectors.toCollection(LinkedHashSet::new));
    }

    private static String groupByCharacters(String str) {
        return str.chars()
                  .sorted()
                  .mapToObj(p -> (char) p)
                  .map(Object::toString)
                  .collect(Collectors.joining());
    }
}
