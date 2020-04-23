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

        InputStreamReader reader = new InputStreamReader(inputStream, charset);
        HashMap<ArrayList<Character>, TreeSet<String>> anagrams = new HashMap<>();
        ArrayList<Character> chars = new ArrayList<>();

        while (true) {
            Character letter = readNext(reader);

            if (letter == null) {
                addWord(chars, anagrams);
                break;
            }

            if (letter == '\r')
                continue;

            if (letter == '\n') {
                addWord(chars, anagrams);
            } else if (!Character.UnicodeBlock.of(letter).equals(Character.UnicodeBlock.CYRILLIC)) {
                chars.clear();
                skipWord(reader);
            } else {
                chars.add(Character.toLowerCase(letter));
            }
        }

        ArrayList<Set<String>> result = new ArrayList<>();
        for (Set<String> set : anagrams.values()){
            if (set.size() > 1)
                result.add(set);
        }

        return result;
    }

    private static Character readNext(InputStreamReader reader){
        int code = 0;
        try {
            code = reader.read();
        } catch (Exception e){
            e.printStackTrace();
        }

        if (code == -1){
            return null;
        }
        return (char)code;
    }

    private static void skipWord(InputStreamReader reader){
        while (true){
            Character letter = readNext(reader);
            if (letter == null || letter =='\n')
                return;
        }
    }

    private static void addWord(ArrayList<Character> chars,
                                HashMap<ArrayList<Character>, TreeSet<String>> anagrams){
        if (chars.size() > 2){
            String word = joinWord(chars);
            Collections.sort(chars);
            if (!anagrams.containsKey(chars)){
                anagrams.put(chars, new TreeSet<>());
            }
            anagrams.get(chars).add(word);
        }
        chars.clear();
    }

    private static String joinWord(List<Character> chars){
        String result = "";
        for(char c : chars)
            result += c;
        return result;
    }
}
