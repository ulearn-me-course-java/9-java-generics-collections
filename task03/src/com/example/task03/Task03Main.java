package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
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
        Map<Integer, ArrayList<String>> sortedBySize = bySize(inputStream);
        Map<String, TreeSet<String>> result = new TreeMap<>();
        for (ArrayList<String> curArr : sortedBySize.values()) {
            for(String s : curArr) {
                char[] symbols = s.toCharArray();
                Arrays.sort(symbols);
                String key = new String(symbols);
                result.computeIfAbsent(key, a -> new TreeSet<>()).add(s);
            }
        }
        return result.values()
                .stream()
                .filter(x -> x.stream().allMatch(y -> y.length() >= 3) && x.size() >= 2)
                .collect(Collectors.toList());
    }
    private static boolean isWordCorrect(String word) {
        for (int i = 0; i < word.length(); i++) {
            if (!(Character.UnicodeBlock.of(word.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)))
                return false;
        }
        return true;
    }
    private static boolean isDuplicate(String word, ArrayList<String> cList) {
        if (cList == null) return false;
        for(String c : cList) {
            if(c.equals(word)) return true;
        }
        return false;
    }
    private static Map<Integer, ArrayList<String>> bySize(InputStream inputStream) {
        Scanner scan = new Scanner(inputStream, Charset.forName("windows-1251")).useDelimiter("\r\n");
        HashMap<Integer, ArrayList<String>> wordsBySize = new HashMap<>();
        ArrayList<String> curSize = new ArrayList<>();
        wordsBySize.put(0, curSize);
        while (scan.hasNext()) {
            String curr = scan.next();
            if (isWordCorrect(curr)) {
                if (!isDuplicate(curr, wordsBySize.get(curr.length())) || curr.length() > 2) {
                    addToMap(wordsBySize, curr);
                }
            }
        }
        return wordsBySize.entrySet()
                .stream()
                .distinct()
                .filter(x -> x.getValue().size() > 1)
                .collect(Collectors.toMap(e -> e.getKey(), e -> e.getValue()));
    }
    private static void addToMap(HashMap<Integer, ArrayList<String>> wordsBySize, String curr) {
        if (!wordsBySize.containsKey(curr.length())) {
            ArrayList<String> curSize = new ArrayList<>();
            curSize.add(curr);
            wordsBySize.put(curr.length(), curSize);
        } else {
            ArrayList<String> currentList = wordsBySize.get(curr.length());
            currentList.add(curr);
        }
    }
}
