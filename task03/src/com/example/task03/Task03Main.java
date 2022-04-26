package com.example.task03;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Pattern;

public class Task03Main {
    private static final Pattern PATTERN = Pattern.compile("^[а-яё]+$");

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"),
                                                                        Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        Map<Integer, Set<String>> wordsBySize = getWordsBySize(inputStream, charset);

        Map<String, Set<String>> result = new TreeMap<>();
        for (Set<String> value : wordsBySize.values()) {
            for (String s : value) {
                char[] symbols = s.toCharArray();
                Arrays.sort(symbols);
                String key = new String(symbols);

                Set<String> words;
                if(result.containsKey(key)){
                    words = result.get(key);
                }
                else{
                    result.put(key, words = new TreeSet<>());
                }
                words.add(s);
            }
        }
        return new ArrayList<>(result.values());
    }

    public static Map<Integer, Set<String>> getWordsBySize(InputStream inputStream, Charset charset) {
        // Integer - количество букв в слове, Set<String> - слова
        Map<Integer, Set<String>> wordsBySize = new HashMap<>();
        Scanner scanner = new Scanner(inputStream, charset.name()).useDelimiter("\n");

        while(scanner.hasNext()){
            String line = scanner.next().toLowerCase();
            if(line.length() > 2 && PATTERN.matcher(line).matches()){
                Set<String> words;
                if(wordsBySize.containsKey(line.length())){
                    words = wordsBySize.get(line.length());
                }
                else{
                    wordsBySize.put(line.length(), words = new HashSet<>());
                }
                words.add(line);
            }
        }

        wordsBySize.entrySet().removeIf(integerSetEntry -> integerSetEntry.getValue().size() < 2);
        return wordsBySize;
    }
}
