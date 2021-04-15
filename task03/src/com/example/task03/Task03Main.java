package com.example.task03;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        Scanner scanner = new Scanner(inputStream, charset.name());
        Map<String, Set<String>> map = new HashMap<>();
        while (scanner.hasNext()) {
            String line = scanner.nextLine().toLowerCase();
            String newLine = getCharsInOrder(line);
            if (newLine == null) continue;
            if (!map.containsKey(newLine))
                map.put(newLine, new HashSet<>());
            map.get(newLine).add(line);
        }
        return getAnagramsByMap(map);
    }

    private static List<Set<String>> getAnagramsByMap(Map<String, Set<String>> map){
        Set<String> keys = new HashSet<>(map.keySet());
        for (String key : keys) if (map.get(key).size() < 2) map.remove(key);
        for (Map.Entry<String, Set<String>> entry : map.entrySet())
            entry.setValue(entry.getValue().stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new)));//Сортировка коллекций внутри
        TreeSet<Set<String>> treeSetForCompareFirstLetter = new TreeSet<>(Comparator.comparingInt(x -> x.stream().findFirst().get().charAt(0)));
        TreeSet<Set<String>> treeSetForCompareLength = new TreeSet<>((x, y) -> x.size());
        treeSetForCompareFirstLetter.addAll(map.values());
        treeSetForCompareLength.addAll(treeSetForCompareFirstLetter);
        return new ArrayList<>(treeSetForCompareLength);
    }

    private static String getCharsInOrder(String line) {
        if (line.length() < 3) return null;
        char[] chars = line.toCharArray();
        Arrays.sort(chars);
        for (char c : chars) {
            if (c > 1103 || c < 1072) return null; //1072 - а 1103 - я
        }
        return String.valueOf(chars);
    }

}
