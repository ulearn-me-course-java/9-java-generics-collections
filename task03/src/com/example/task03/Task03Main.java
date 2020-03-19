package com.example.task03;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.defaultCharset());
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        HashSet<String> set = parse(inputStream, Charset.defaultCharset());
        return null;
    }

    private static HashSet<String> parse(InputStream inputStream, Charset charset){
        Scanner sc = new Scanner(inputStream).useDelimiter("\n");
        HashSet<String> set = new HashSet<>();
        while (sc.hasNext()){
            String next = sc.next();
            if(next.length() < 3 || !isCyrillic(next)) continue;
            next = next.toLowerCase();
            set.add(next);
        }
        return set;
    }

    public static boolean isCyrillic(String s) {
        for (char a : s.toCharArray()) {
            if (Character.UnicodeBlock.of(a) != Character.UnicodeBlock.CYRILLIC) {
                return false;
            }
        }
        return true;
    }
}
