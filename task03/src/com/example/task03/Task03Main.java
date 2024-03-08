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
        List<Set<String>> anagramGroups = new ArrayList<>();
        Map<String, Set<String>> anagramMap = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String word = line.toLowerCase();
                String sortedWord = sortCharacters(word);

                if(sortedWord.length() < 3 || !sortedWord.matches("[а-яё]+"))
                    continue;

                if (!anagramMap.containsKey(sortedWord)) {
                    SortedSet<String> anagramGroup = new TreeSet<>();
                    anagramGroup.add(word);
                    anagramGroups.add(anagramGroup);
                    anagramMap.put(sortedWord, anagramGroup);
                } else {
                    Set<String> anagramGroup = anagramMap.get(sortedWord);
                    anagramGroup.add(word);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        Filter(anagramGroups);
        sortAnagrams(anagramGroups);

        return anagramGroups;
    }

    public static void Filter(List<Set<String>> collection){
        Iterator<Set<String>> it = collection.listIterator();
        while(it.hasNext()){
            Set<String> set = it.next();
            if(set.size() <= 1)
                it.remove();
        }
    }

    public static void sortAnagrams(List<Set<String>> anagramGroups) {
        anagramGroups.sort(Comparator.comparing(Task03Main::getFirstElement));
    }

    private static String getFirstElement(Set<String> set) {
        return set.stream().findFirst().orElse("");
    }

    private static String sortCharacters(String word) {
        char[] charArray = word.toLowerCase().toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
}
