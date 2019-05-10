package com.example.task03;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

        Scanner scanner = new Scanner(new InputStreamReader(inputStream, charset));
        ArrayList<String> allWords = new ArrayList<>();

        Hashtable<String, TreeSet<String>> dictionary = new Hashtable<>();

        while(scanner.hasNext()){
            String word = scanner.next().toLowerCase();

            if(!isCorrect(allWords, word))
                continue;

            String key = sortAlphabetically(word);

            if(!dictionary.containsKey(key))
                dictionary.put(key, new TreeSet<>());

            TreeSet<String> list = dictionary.get(key);

            if(!list.contains(word))
                list.add(word);

            allWords.add(word);
        }

        List<Map.Entry<String, TreeSet<String>>> entryList = new ArrayList<>(dictionary.entrySet());
        Collections.sort(entryList, Comparator.comparing(map -> map.getValue().first()));

        List<Set<String>> result = new ArrayList<>();

        for(int i = 0; i < entryList.size(); i++){
            if(entryList.get(i).getValue().size() > 1)
                result.add(new TreeSet<>(entryList.get(i).getValue()));
        }

        return result;
    }

    private static boolean isCorrect(List<String> allWorlds, String word)
    {
        if(word.length() < 3 || allWorlds.contains(word))
            return false;

        for(int i = 0; i < word.length(); i++) {
            if (!Character.UnicodeBlock.of(word.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC))
                return false;
        }

        return true;
    }

    private static String sortAlphabetically(String word) {
        char[] charArray = word.toCharArray();

        Arrays.sort(charArray);

        return new String(charArray);
    }
}
