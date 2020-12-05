package com.example.task03;


import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Task03Main
{
    public static void main(String[] args) throws IOException
    {
        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams)
        {
            System.out.println(anagram);
        }
    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset)
    {
        List<String> words = getWords(inputStream, charset);
        HashMap<Set<Character>, Set<String>> wordsLetters = getWordsLetters(words);
        return getAnagrams(wordsLetters);
    }

    private static List<String> getWords(InputStream inputStream, Charset charset)
    {
        List<String> words = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset)))
        {
            String word;
            while ((word = reader.readLine()) != null)
                if (correctInput(word) && !words.contains(word))
                    words.add(word.toLowerCase());
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return words;
    }

    private static boolean correctInput(String word)
    {
        return word.length() >= 3 && Pattern.matches("[а-яА-Я]+", word);
    }

    private static HashMap<Set<Character>, Set<String>> getWordsLetters(List<String> words)
    {
        HashMap<Set<Character>, Set<String>> wordsLetters = new HashMap<>();
        for (String word : words)
        {
            Set<Character> letters = word.chars()
                    .mapToObj(e -> (char) e)
                    .collect(Collectors.toCollection(TreeSet::new));

            if (wordsLetters.containsKey(letters))
                wordsLetters.get(letters).add(word);
            else
            {
                Set<String> anagrams = new TreeSet<>();
                anagrams.add(word);
                wordsLetters.put(letters, anagrams);
            }
        }
        return wordsLetters;
    }

    private static List<Set<String>> getAnagrams(HashMap<Set<Character>, Set<String>> wordsLetters)
    {
        List<Set<String>> result = new ArrayList<>();
        for (Set<String> words : wordsLetters.values())
        {
            if (words.size() >= 2)
                result.add(words);
        }
        result.sort(new AnagramsComparator());
        return result;
    }

    static class AnagramsComparator implements Comparator<Set<String>>
    {
        @Override
        public int compare(Set<String> first, Set<String> second)
        {
            String[] arr1 = new String[first.size()];
            String[] arr2 = new String[second.size()];
            arr1 = first.toArray(arr1);
            arr2 = second.toArray(arr2);
            return arr1[0].compareTo(arr2[0]);
        }
    }
}
