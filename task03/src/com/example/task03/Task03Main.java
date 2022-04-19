package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

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
        ArrayList<String> words = readWords(inputStream, charset);
        List<Set<String>> result = new ArrayList<>();

        Set<String> setForResult = new TreeSet<>();

        for (int i = 0; i < words.size(); i++)
        {
            Set<Character> selectedWord = returnSetWord(words.get(i).toLowerCase().toCharArray());
            if (checkRussianLang(words.get(i)) && selectedWord.size() >= 3)
            {
                setForResult.add(words.get(i).toLowerCase());
                removeAll(words, words.get(i));

                for (int j = 0; j < words.size(); j++)
                {
                    Set<Character> comparableWord = returnSetWord(words.get(j).toLowerCase().toCharArray());
                    if (comparableWord.equals(selectedWord))
                    {
                        setForResult.add(words.get(j).toLowerCase());
                        removeAll(words, words.get(j));
                    }
                }
                if (setForResult.size() > 1)
                {
                    result.add(setForResult);
                    setForResult = new TreeSet<>();
                }
            }
        }
        Collections.sort(result, ((o1, o2) ->
        {
            String el1 = o1.stream().findFirst().get();
            String el2 = o2.stream().findFirst().get();
            return el1.compareTo(el2);
        }));
        return result;
    }

    private static boolean checkRussianLang(String word)
    {
        char[] charWord = word.toCharArray();
        for (char letter : charWord)
        {
            if (Character.toLowerCase(letter) < 'а' || Character.toLowerCase(letter) > 'я')
                return false;
        }
        return true;
    }

    private static void removeAll(ArrayList<String> list, String value)
    {
        for (int i = 0; i < list.size(); i++)
        {
            if (value.equals(list.get(i)))
                list.remove(value);
        }
    }

    private static Set<Character> returnSetWord(char[] word)
    {
        Set<Character> characters = new HashSet<>();
        for (char letter : word)
        {
            characters.add(letter);
        }
        return characters;
    }

    private static ArrayList<String> readWords(InputStream inputStream, Charset charset)
    {
        ArrayList<String> list = new ArrayList<>();
        try (Scanner obj = new Scanner(inputStream, charset.name()))
        {
            while (obj.hasNext())
            {
                list.add(obj.next());
            }
        }
        catch (Exception exception)
        {
            exception.getMessage();
        }
        return list;
    }
}