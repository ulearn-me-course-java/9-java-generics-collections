package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        TreeMap<String, Set<String>> map = new TreeMap<String, Set<String>>();
        try(BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset))) {

            List<String> strings = validateStrings(bufferedReader);

            setAnagramsToMap(map, strings);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return filterBySize(map);
    }

    private static void setAnagramsToMap(Map<String, Set<String>> map, List<String> list) {
        for(String word : list) {
            char[] symbols = word.toCharArray();
            Arrays.sort(symbols);
            String key = new String(symbols);
            map.computeIfAbsent(key, table -> new TreeSet<>()).add(word); //compute для создания нового ключа, если не существ.
                                                                        //если уже существует, то возвращает элем по ключу key
        }
    }

    private static List<Set<String>> filterBySize(TreeMap<String, Set<String>> map) {
        return map.values()
                .stream().filter(word -> word.size() >= 2)
                .collect(Collectors.toList());
    }

    private static List<String> validateStrings(BufferedReader bufferReader) {
        Stream<String> inputStream = bufferReader.lines();

        List<String> strings = inputStream.map(word -> word.toLowerCase())               //приводим к lower
                .filter(word -> word.length() >= 3)            //не входят те, что меньше 3 в длину
                .filter(word -> word.matches("[а-яё]+")) //все буквенные слова
                .sorted()
                .collect(Collectors.toList());

        return strings;
    }
}
