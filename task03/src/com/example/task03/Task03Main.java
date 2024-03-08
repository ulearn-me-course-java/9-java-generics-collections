package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Set;
import java.util.*;


public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset)
    {
        List<Set<String>> group = new ArrayList<>();
        Map<String, Set<String>> anagramMap = new HashMap<>();

        try (BufferedReader buffer = new BufferedReader(new InputStreamReader(inputStream, charset)))
        {
            String string;
            while ((string = buffer.readLine()) != null)
            {
                String word = string.toLowerCase();
                String wordSorted = sortCharacters(word);

                if(wordSorted.length() < 3 || !wordSorted.matches("[а-яё]+"))
                    continue;

                if (!anagramMap.containsKey(wordSorted))
                {
                    SortedSet<String> anagramGroup = new TreeSet<>();
                    anagramGroup.add(word);
                    group.add(anagramGroup);
                    anagramMap.put(wordSorted, anagramGroup);
                } else
                {
                    Set<String> anagramGroup = anagramMap.get(wordSorted);
                    anagramGroup.add(word);
                }
            }
        } catch (IOException e)
        {
            e.printStackTrace();
        }

        Filter(group);
        sortAnagrams(group);

        return group;
    }

    public static void Filter(List<Set<String>> collection)
    {
        Iterator<Set<String>> it = collection.listIterator();
        while(it.hasNext())
        {
            Set<String> set = it.next();
            if(set.size() <= 1)
                it.remove();
        }
    }

    public static void sortAnagrams(List<Set<String>> anagramGroups)
    {
        anagramGroups.sort(Comparator.comparing(Task03Main::getFirstElement));
    }

    private static String getFirstElement(Set<String> set)
    {
        return set.stream().findFirst().orElse("");
    }

    private static String sortCharacters(String word)
    {
        char[] charArray = word.toLowerCase().toCharArray();
        Arrays.sort(charArray);
        return new String(charArray);
    }
}
