package com.example.task03;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.*;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset)   {
        //фильтрую группы и группирую по длине
        Map<Integer, Set<String>> groupingWords = getWordsBySize(inputStream, charset);

        Map<String, Set<String>> result = new TreeMap<>(); //отсорт по буквам в словах
        for (Set<String> value : groupingWords.values()) {
            for (String s : value) {
                char[] symbols = s.toCharArray();
                Arrays.sort(symbols);
                String key = new String(symbols);

                Set<String> words;
                if(result.containsKey(key)){
                    words = result.get(key);
                }
                else{
                    result.put(key, words = new TreeSet<>());
                }
                words.add(s);
            }
        }
        return new ArrayList<>(result.values());
    }

    public static Map<Integer, Set<String>> getWordsBySize(InputStream inputStream, Charset charset) {
        //кол-во букв, слова
        Map<Integer, Set<String>> groupingWords = new HashMap<>();
        Scanner scanner = new Scanner(inputStream, charset.name()).useDelimiter("\n");

        while(scanner.hasNext()){
            String line = scanner.next().toLowerCase();
            if(line.length() > 2 && line.matches("[а-яё]+")){
                Set<String> words;
                if(groupingWords.containsKey(line.length())){
                    words = groupingWords.get(line.length());//использую текущее
                }
                else{
                    groupingWords.put(line.length(), words = new HashSet<>()); //создаю новое множество
                }
                words.add(line); //добавила ориг слово в анаграммы
            }
        }
        //удаляю слова набор <2 слов
        groupingWords.entrySet().removeIf(integerSetEntry -> integerSetEntry.getValue().size() < 2);
        return groupingWords;
    }
}
