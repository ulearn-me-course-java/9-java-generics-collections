package com.example.task03;

import java.io.*;
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
        Map<Set<Character>, SortedSet<String>> map = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, charset))){
            String line;
            while((line = reader.readLine()) != null){ // добавлем пока не закончится поток

            if (!line.matches("^[а-яА-Я]+$") || line.length()<3) continue; // проверяем что только русские смволы и длина больше 3

                Set<Character> chars = new HashSet<>();
                for(Character c : line.toLowerCase().toCharArray()){ // собираем сет их charcter
                    chars.add(c);
                }

                if(map.containsKey(chars))
                    map.get(chars).add(line.toLowerCase());
                else
                    map.put(chars, new TreeSet<String>(Arrays.asList(line.toLowerCase()))); // в зависимости от того есть ли такое множество букв или нет добавляем или создаем новый список

            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        List<Set<String>> result = new ArrayList<Set<String>>(); // итоговый список
        for(Set<Character> s : map.keySet()){
            if(map.get(s).size()< 2 ) continue; // Добавляем к результату список только если он минимум из 2 состоит
            result.add(map.get(s));
        }
        Collections.sort(result, new Comparator<Set<String>>() {
            @Override
            public int compare(Set<String> o1, Set<String> o2) {
                for (String s : o1){
                    for (String s2 : o2){
                        return s.compareTo(s2);
                    }
                }
                throw new RuntimeException("Sets are empty"); // сортируем коллекцию результата 
            }
        });

        return result;
    }
}
