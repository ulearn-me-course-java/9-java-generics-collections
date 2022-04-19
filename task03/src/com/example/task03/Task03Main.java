package com.example.task03;

import sun.security.util.ArrayUtil;

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
        System.out.println((int) 'Т');
        System.out.println((int) 'т');
        System.out.println((int) '-');
        /*
        List<Set<String>> listSet = new ArrayList<>();
        Set<String> words = new TreeSet<>();
        words.add("ya");
        words.add("world");
        words.add("hello");
        listSet.add(words);
        words = new TreeSet<>();
        words.add("aaa");
        words.add("cccc");
        words.add("bbb");
        listSet.add(words);
        String[] list = words.toArray(new String[0]);
        Arrays.sort(list);
        for (Set<String> word : listSet)
        {
            System.out.println(word);
        }
        */
    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset)
    {
        ArrayList<String> words = readWords(inputStream, charset);
        List<Set<String>> result = new ArrayList<>(); // то, что будет возвращаться

        Set<String> setForResult = new TreeSet<String>(); // этот набор добавляется в result,
        // обнуляется после добавления

        for (int i = 0; i < words.size(); i++)
        {
            Set<Character> selectedWord = returnSetWord(words.get(i).toLowerCase().toCharArray());
            if (checkRussianLang(words.get(i)) && selectedWord.size() >= 3)
            {
                setForResult.add(words.get(i).toLowerCase());
                removeAll(words, words.get(i));

                for (int j = 0; j < words.size(); j++)
                {
                    String element = words.get(j);
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
            char upperLetter = Character.toUpperCase(letter);
            if (upperLetter < 'А' || upperLetter > 'Я')
                return false;
        }
        return true;
    }

    private static void removeAll(ArrayList<String> list, String value)
    {
        ArrayList<String> results = new ArrayList<>();
        for (String element : list)
        {
            if (value.equals(element)) results.add(element);
        }
        for (String result : results)
        {
            list.remove(result);
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
