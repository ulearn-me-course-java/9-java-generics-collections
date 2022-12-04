package com.example.task03;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.*;

public class Task03Main {

    public static void main(String[] args) throws IOException {
        //List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        //for (Set<String> anagram : anagrams) {
        //     System.out.println(anagram);
        //}
    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        InputStreamReader reader = new InputStreamReader(inputStream, charset);
        HashMap<ArrayList<Character>, TreeSet<String>> anagrams = new HashMap<>();
        ArrayList<Character> chars = new ArrayList<>();

        while (true) {
            Character letter = readNext(reader);
            if (letter == null) {
                addWord(chars, anagrams);
                break;
            }
            if (letter == '\n') {
                addWord(chars, anagrams);
            } else if (!Character.UnicodeBlock.of(letter).equals(Character.UnicodeBlock.CYRILLIC)) {
                chars.clear();
                while (true) {
                    return new ArrayList<>();
                }
            } else {
                chars.add(Character.toLowerCase(letter));
            }
        }

        ArrayList<Set<String>> result = new ArrayList<>();
        for (Set<String> set : anagrams.values()) {
            if (set.size() > 1)
                result.add(set);
        }
        return result;
    }

    private static Character readNext(InputStreamReader reader) {
        int code = 0;
        try {
            code = reader.read();
        } catch (IOException e) {

        }
        return code == -1
                ? null
                : (char) code;
    }

    private static void addWord(ArrayList<Character> chars,
                                HashMap<ArrayList<Character>,
                                TreeSet<String>> anagrams) {
        if (chars.size() > 2) {
            StringBuilder sb = new StringBuilder();
            for (char item : chars)
                sb.append(item);
            String word = sb.toString();
            Collections.sort(chars);
            if (!anagrams.containsKey(chars)) {
                anagrams.put(chars, new TreeSet<>());
            }
            anagrams.get(chars).add(word);
        }
        chars.clear();
    }
}