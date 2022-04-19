package com.example.task03;

import javafx.util.Pair;
import org.assertj.core.util.Strings;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        List<String> words = readWords(inputStream, charset);
        Stream<Pair<String, String>> eachWordSortedByChar = words.stream().map(w -> {
            char[] arr = w.toCharArray();
            Arrays.sort(arr);
            return new Pair(String.copyValueOf(arr), w);
        });
        Map<String, Set<String>> map = new HashMap<>();
        eachWordSortedByChar.forEach(w -> {
            if (!map.containsKey(w.getKey())){
                Set<String> a = new HashSet<String>();
                a.add(w.getValue());
                map.put(w.getKey(), a);
            }
            else{
                map.get(w.getKey()).add(w.getValue());
            }
        });

        List<Set<String>> list = map.entrySet().stream().map(x -> x.getValue()).collect(Collectors.toList());
        list = list.stream().filter(x -> x.size() > 1).collect(Collectors.toList());
        list = list.stream().map(x -> x.stream().sorted().collect(Collectors.toCollection(LinkedHashSet::new))).collect(Collectors.toList());
        list = list.stream().sorted(Comparator.comparing(o -> o.stream().findFirst().get())).collect(Collectors.toList());
        return list;
        //
    }
    public static List<String> readWords(InputStream inputStream, Charset charset){
        List<String> stringList = new LinkedList<String>();
        try{
            Scanner sc = new Scanner(inputStream, charset.name());
            sc.useDelimiter("\n");

            while(sc.hasNext()){
                String word = sc.next().toLowerCase().replace('\r', '\0');
                if (russianWordCheck(word) && word.length() > 2 && !stringList.contains(word))
                    stringList.add(word);
            }

        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return stringList;
    }
    public static boolean russianWordCheck(String word){
        for(char c : word.toCharArray()){
            int i = c;
            if (!(i <= 'я' && i >= 'а' || i == 'ё' || i == '\0' || i == ' '))
                return false;
        }
        return true;
    }
}