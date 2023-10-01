package com.example.task03;

import javafx.scene.shape.Path;

import java.io.*;
import java.nio.charset.Charset;
import java.util.*;
import java.util.function.Consumer;
import java.util.stream.Collectors;

import static java.nio.file.Files.newBufferedReader;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"),
                Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }
    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        Scanner scanner = new Scanner(inputStream, charset.name());
        ArrayList<Set<String>> resultList = new ArrayList<Set<String>>();
        while(scanner.hasNext()){
            String word = scanner.next().toLowerCase(Locale.ROOT);
            if (word.length()>=3){
                boolean flag = true;
                for (Set<String> stringSet:
                     resultList) {
                    Iterator<String> iterator = stringSet.iterator();
                    String oldWord = iterator.next();
                    if (IsAnagramm( oldWord, word)){
                        stringSet.add(word);
                        flag = false;
                        break;
                    }
                }
                if (flag){
                    Set<String> newStringSet = new LinkedHashSet<>();
                    newStringSet.add(word);
                    resultList.add(newStringSet);
                }
            }
        }
        for (int i=0;i<resultList.size();i++) {
            if (resultList[i].size()==1){
                resultList.remove(stringSet);
            }else {
                stringSet = stringSet.stream().sorted().collect(Collectors.toSet());
            }
        }
        resultList.stream().sorted();
        return resultList;
    }

    private static boolean IsAnagramm(String word, String anotherWord) {
        if (anotherWord.length() == word.length()){
            for (Character letter:
                 anotherWord.toCharArray()) {
                int letterCountInAnotherWord = 0;
                int letterCountInWord = 0;
                for (Character letterInAnotherWord:
                        anotherWord.toCharArray()) {
                    if (letterInAnotherWord==letter){
                        letterCountInAnotherWord++;
                    }
                }
                for (Character letterInWord:
                        word.toCharArray()) {
                    if (letterInWord==letter){
                        letterCountInAnotherWord++;
                    }
                }
                if (letterCountInWord!=letterCountInAnotherWord){
                    return false;
                }
            }
            return true;
        }

        return false;
    }
}
