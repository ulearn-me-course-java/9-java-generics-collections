package com.example.task03;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
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
        List<Set<String>> answer = new ArrayList<>();
        LinkedList<TreeSet<String>> list = new LinkedList<>();
        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNext()){
            String tempStr = scanner.next().toLowerCase();

            if (Pattern.matches(".*\\p{InCyrillic}.*", tempStr) && !tempStr.contains("-") && tempStr.length() >= 3){
                TreeSet<String> tempSet = findSet(list, tempStr);
                list.remove(tempSet);
                tempSet.add(tempStr);
                addSetInList(list, tempSet);
            }
        }

        for (TreeSet<String> set : list) {
            if (set.size() >= 2){
                answer.add(set);
            }
        }

        return answer;
    }

    private static TreeSet<String> findSet(LinkedList<TreeSet<String>> list, String string){
        //Метод находит подходящий сет и возвращает его.
        for (TreeSet<String> set : list) {
            String firstSetEl = (String)set.toArray()[0];
            if (getHashCode(firstSetEl) == getHashCode(string)){
                return set;
            }
        }
        //Если сета не найдено, то возвращается новый.
        return new TreeSet<>();
    }

    private static void addSetInList(LinkedList<TreeSet<String>> list, TreeSet<String > set){
        for (TreeSet<String> listSet : list) {
            if (listSet.first().compareTo(set.first()) > 0){
                list.add(list.indexOf(listSet), set);
                return;
            }
        }
        list.addLast(set);
    }

    private static int getHashCode(String string){
        //Хэш код одинаков для анаграмм.
        int hashCode = 0;
        for (char ch : string.toCharArray()) {
            hashCode += Objects.hashCode(ch);
        }
        return hashCode;
    }
}
