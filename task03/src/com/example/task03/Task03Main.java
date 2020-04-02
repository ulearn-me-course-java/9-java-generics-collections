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
        List<String> words = new ArrayList<>();

        try {
            String word;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset));
            while ((word = bufferedReader.readLine()) != null) {
                if (!words.contains(word) && isWordCorrect(word))
                    words.add(word.toLowerCase());
            }
        } catch (IOException e) {
            //ignore
        }

        HashMap<String, TreeSet<String>> anagramsMap = new HashMap<>();
        for (String word : words) {
            String key = getHashSetKey(anagramsMap.keySet(), word);
            if (key == null) {
                TreeSet<String> set = new TreeSet<>();
                set.add(word);
                anagramsMap.put(word, set);
            } else {
                anagramsMap.get(key).add(word);
            }
        }

        ArrayList<Set<String>> anagrams = new ArrayList<>();
        for (String key : anagramsMap.keySet()) {
            if (anagramsMap.get(key).size() > 1) {
                anagrams.add(anagramsMap.get(key));
            }
        }
        return anagrams;
    }

    private static boolean isWordCorrect(String word) {
        if (word.length() < 3) {
            return false;
        }
        for (int i = 0; i < word.length(); i++) {
            if (!Character.UnicodeBlock.of(word.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)) {
                return false;
            }
        }
        return true;
    }

    private static String getHashSetKey(Set<String> keySet, String word) {
        for (String key : keySet) {
            if (isAnagramTo(key, word)) {
                return key;
            }
        }
        return null;
    }

    private static boolean isAnagramTo(String source, String resource) {
        if (source.length() != resource.length()) {
            return false;
        }
        char[] sourceChars = source.toCharArray();
        char[] resourceChars = resource.toCharArray();
        Arrays.sort(sourceChars);
        Arrays.sort(resourceChars);
        return Arrays.equals(sourceChars, resourceChars);
    }
}
