package com.example.task03;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.*;

public class Task03Main {
    static SortedSet<String> list;
    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }
    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset){
        Map<String, TreeSet<String>> map = new LinkedHashMap<>();
        TreeSet<String> list = new TreeSet<>();
        try(BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))) {
            String str;
            while((str = reader.readLine()) != null){
                str = str.toLowerCase();
                if(str.length() > 2 && str.matches("[а-яё]+")){
                    list.add(str);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        for(String elem : list){
            char[] charArray = elem.toCharArray();
            Arrays.sort(charArray);
            String str = Arrays.toString(charArray);
            if(map.containsKey(str)){
                map.get(str).add(elem);
            }
            else{
                map.put(str, new TreeSet<>());
                map.get(str).add(elem);
            }
        }
        return getList(map);
    }

    private static List<Set<String>> getList(Map<String, TreeSet<String>> map){
        List<Set<String>> anagrams = new ArrayList<>();
        for(Map.Entry<String, TreeSet<String>> entry : map.entrySet()){
            if(entry.getValue().size() >= 2){
                anagrams.add(entry.getValue());
            }
        }
        return anagrams;
    }

}
