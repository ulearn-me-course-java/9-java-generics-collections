package com.example.task03;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Task03Main {
    private static final Pattern RUSSIAN_WORD_PATTERN = Pattern.compile("^[а-яА-ЯёЁ]+$");
    
    public static void main(String[] args) throws IOException {
        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }
        
    }
    
    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        Map<Integer, Set<String>> wordsBySize = getWordsBySize(inputStream, charset);
        
        Map<String, Set<String>> result = new TreeMap<>();
        for (Set<String> curArr : wordsBySize.values()) {
            for (String s : curArr) {
                char[] symbols = s.toCharArray();
                Arrays.sort(symbols);
                String key = new String(symbols);
                result.computeIfAbsent(key, a -> new TreeSet<>()).add(s);
            }
        }
        return new ArrayList<>(result.values());
    }
    
    private static Map<Integer, Set<String>> getWordsBySize(InputStream inputStream, Charset charset) {
        Scanner scanner = new Scanner(inputStream, charset.name()).useDelimiter("\n");
        Map<Integer, Set<String>> wordsBySize = new HashMap<>();
        while (scanner.hasNext()) {
            String word = scanner.next().toLowerCase();
            if (word.length() > 2 && RUSSIAN_WORD_PATTERN.matcher(word).matches()) {
                wordsBySize.computeIfAbsent(word.length(), a -> new HashSet<>()).add(word);
            }
        }
        return wordsBySize.entrySet()
            .stream()
            .filter(x -> x.getValue().size() > 1)
            .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
    }
}
