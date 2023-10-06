package com.example.task03;

import javafx.scene.shape.Path;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.nio.file.Files.newBufferedReader;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"),
                Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }


        Tests tests = new Tests();
        tests.test();
        tests.testExample();
        tests.testEmpty();
        tests.testErrors();
    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset){

        TreeMap<String,TreeSet<String>> anagramsMap = new TreeMap<>();

        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            Stream<String> stringStream = br.lines();
            List<String> wordslist= stringStream.map(s -> s.toLowerCase()).sorted()
                    .filter(x -> x.length()>=3)
                    .filter(x -> x.matches("[а-ёя]+"))
                    .collect(Collectors.toList());

            for (String word : wordslist) {
                char[] wordChars = word.toCharArray();
                Arrays.sort(wordChars);

                String key = new String(wordChars);
                TreeSet<String> value = new TreeSet<>();

                anagramsMap.putIfAbsent(key,value);
                anagramsMap.get(key).add(word);
            }
        }catch (IOException e){
            e.printStackTrace();
        }

        List<Set<String>> resultList = new ArrayList<>();

        for (TreeSet<String> values : anagramsMap.values()) {
            if (values.size() >= 2){
                Set<String> setString = new TreeSet<>();
                setString.addAll(values);

                resultList.add(setString);
            }
        }
        return resultList;
    }
}
