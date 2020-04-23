package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        /*for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }*/

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        List<String> words = new ArrayList<>();
        try{
            String word;
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, charset));
            while((word = bufferedReader.readLine()) != null){
                if(!words.contains(word) && checkWord(word))
                    words.add(word.toLowerCase());
            }
        } catch (Exception e){
            e.printStackTrace();
        }

        HashMap<String, TreeSet<String>> anagramsWords = new HashMap<>();
        for(String word: words){
            String key = getAnagramsKey(anagramsWords.keySet(), word);
            if(key == null){
                TreeSet<String> set = new TreeSet<>();
                set.add(word);
                anagramsWords.put(word, set);
            }
            else{
                anagramsWords.get(key).add(word);
            }
        }

        ArrayList<Set<String>> anagrams = new ArrayList<>();
        for(String key: anagramsWords.keySet()){
            if(anagramsWords.get(key).size() > 1){
                anagrams.add(anagramsWords.get(key));
            }
        }
        return anagrams;
    }

    public static boolean checkWord(String word){
        if(word.length() < 3) return false;

        for(int i = 0; i< word.length(); i++){
            if(!Character.UnicodeBlock.of(word.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)) return false;
        }
        return true;
    }

    public static String getAnagramsKey(Set<String> keySet, String word){
        for(String key: keySet){
            if(checkLetters(key, word)) return key;
        }
        return null;
    }

    public static boolean checkLetters(String word1, String word2){
        if(word1.length() != word2.length()) return false;
        char[] charWord1 = word1.toCharArray();
        char[] charWord2 = word2.toCharArray();
        Arrays.sort(charWord1);
        Arrays.sort(charWord2);
        return Arrays.equals(charWord1, charWord2);
    }
}
