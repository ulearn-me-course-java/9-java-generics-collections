package com.example.task03;

import java.io.*;
import java.lang.reflect.Array;
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
        TreeSet<String> anagram;


        Set<TreeSet<String>> result = new TreeSet<>(Comparator.comparing(TreeSet::first));
        List<String> input = new ArrayList<>();
        List<String> passed = new ArrayList<>();

        new BufferedReader(new InputStreamReader(inputStream, charset)).lines().forEach(input::add);

        for (int i = 0; i<input.size(); i++){
            String s1 = input.get(i);

            if (s1.length() < 3 || passed.contains(s1) || !s1.matches("^[а-яА-Я]+$")) continue;

            anagram = new TreeSet<>();

            for (int j = i+1; j < input.size(); j++){
                String s2 = input.get(i);

                s1= s1.toLowerCase();
                s2 = s2.toLowerCase();

                if(s1.equals(s2)) break;

                char[] c1 = s1.toCharArray();
                char[] c2 = s2.toCharArray();

                Arrays.sort(c1);
                Arrays.sort(c2);

                if(String.valueOf(c1).equals(String.valueOf(c2))){
                    anagram.add(s2);
                    passed.add(s2);
                }
            }

            if(!anagram.isEmpty()){
                anagram.add(s1);
                result.add(anagram);
            }

            passed.add(s1);
        }

        return new ArrayList<>(result);
    }
}
