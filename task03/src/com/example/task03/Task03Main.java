package com.example.task03;

import javafx.collections.transformation.SortedList;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;

public class Task03Main {

    public static void main(String[] args) throws IOException {

      /**  List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }
       */
    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {

        List<Set<String>> anagrams = new ArrayList<>();
        Scanner scanner = new Scanner(inputStream, String.valueOf(charset))
                .useDelimiter("\n");

            while (scanner.hasNext()){
               String word= scanner.next().toLowerCase();
               boolean isAdded=false;
               if(isCorrect(word)){
                   for(Set<String> s : anagrams){
                       if(contains(s,word)) {
                           s.add(word);
                           isAdded=true;
                           break;
                       }
                   }
                   if(!isAdded)
                   {
                       TreeSet<String> set = new TreeSet<>();
                       set.add(word);
                       anagrams.add(set);
                   }
               }
            }
        scanner.close();

        List<Set<String>> result = new ArrayList<>();

            for(Set<String> s: anagrams)
                if(s.size()>1)
                    result.add(s);

         result.sort((o1, o2) -> {
             String s1 = o1.iterator().next();
             String s2 = o2.iterator().next();
             return s1.compareTo(s2);
         });
        return result;
    }

    private static boolean isCorrect(String word){
        if(word.length()<3)
            return false;
        char[] chars = word.toCharArray();
        for (char ch: chars)
            if(!Character.UnicodeBlock.of(ch).equals(Character.UnicodeBlock.CYRILLIC))
                return false;
        return true;
    }
    private static boolean contains(Set<String> set, String word) {
        return areAnagrams(set.iterator().next(),word);
    }

    private static boolean areAnagrams(String word1, String word2) {
        if (word1 == null || word2 == null ||
                word1.length() != word2.length())
            return false;

        char[] ch1 = word1.toLowerCase().toCharArray();
        char[] ch2 = word2.toLowerCase().toCharArray();
        Arrays.sort(ch1);
        Arrays.sort(ch2);
        return Arrays.equals(ch1, ch2);
    }
}
