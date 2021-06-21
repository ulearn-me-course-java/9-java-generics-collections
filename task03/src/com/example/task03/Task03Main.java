package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Pattern;

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
            String value;
            BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset));
            while ((value = br.readLine()) != null){
                if (isWordCorrect(value)) {
                    words.add(value.toLowerCase());
                }
            }
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        words = new ArrayList<>(new LinkedHashSet<>(words)); // удаление дубликатов слов

        HashMap<String, TreeSet<String>> anagramsMap = new HashMap<>();
        for (String word : words){
            String key = getHashSetKey(anagramsMap.keySet(), word);
            if (key == null){
                TreeSet<String> set = new TreeSet<>();
                set.add(word);
                anagramsMap.put(word, set);
            }
            else {
                anagramsMap.get(key).add(word);
            }
        }

        List<Set<String>> anagrams = new ArrayList<>();
        for (String key : anagramsMap.keySet()){
            if (anagramsMap.get(key).size() > 1){
                anagrams.add(anagramsMap.get(key));
            }
        }
        return anagrams;
    }

    private static boolean isWordCorrect(String word){
        if (word.length() < 4)
            return false;
        for (int i = 0; i < word.length(); i++){
            if (!(Character.UnicodeBlock.of(word.charAt(i)).equals(Character.UnicodeBlock.CYRILLIC)))
                return false;
        }
        return true;
    }

    private static String getHashSetKey(Set<String> keySet, String word){
        for (String key : keySet) {
            if (isAnagramsTo(word, key))
                return key;
        }
        return null;
    }

    private static boolean isAnagramsTo(String source, String resource){
        if (source.length() != resource.length())
            return false;
        char[] sourceChars   = source.toCharArray();
        char[] resourceChars = resource.toCharArray();
        Arrays.sort(sourceChars);
        Arrays.sort(resourceChars);
        return Arrays.equals(sourceChars, resourceChars);
    }
}