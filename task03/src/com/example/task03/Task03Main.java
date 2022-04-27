package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        /*List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }*/

        List<String> words = Arrays.asList(
                "трос",
                "накал",
                "рост",
                "чесотка",
                "сорт",
                "отсечка");
        List<Set<String>> anagrams = findAnagrams(new ByteArrayInputStream(String.join("\n", words).getBytes()), Charset.forName("windows-1251"));
        for (Set<String> anagram : anagrams) {
            System.out.println(anagram);
        }

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset){
        Set<String> wordSet = new HashSet<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset))){
            String word;
            while((word = br.readLine()) != null) {
                if(word.length() >= 3 && isCyrillic(word)){
                    wordSet.add(word.toLowerCase(Locale.ROOT));
                }
            }
        } catch (IOException ioException) {
            System.err.println("IOEXCEPTION");
        }

        List<Set<String>> anagramList = new ArrayList<>();
        LinkedList<String> wordList = new LinkedList<>(wordSet);
        while (!wordList.isEmpty()) {
            String mainWord = wordList.getFirst();
            wordList.removeFirst();

            SortedSet<String> set = new TreeSet<>();
            set.add(mainWord);

            Iterator<String> iterator = wordList.iterator();
            while (iterator.hasNext()) {
                String word = iterator.next();
                if (areAnagrams(mainWord, word)) {
                    set.add(word);
                    iterator.remove();
                }
            }

            if (set.first() != set.last())
                anagramList.add(set);
        }

        anagramList.sort((first, second) ->
                ((SortedSet<String>)first).first().compareTo(((SortedSet<String>)second).first()));

        return anagramList;
    }

    public static boolean isCyrillic(String word){
        for(char ch : word.toCharArray()){
            if(Character.UnicodeBlock.of(ch) != Character.UnicodeBlock.CYRILLIC){
                return false;
            }
        }
        return true;
    }

    public static boolean areAnagrams(String s1, String s2){
        if(s1.length() != s2.length()) return false;

        Map<Character, Integer> chars = new HashMap<>();
        for(int i = 0; i < s1.length(); i++){
            Character ch1 = s1.charAt(i);
            Character ch2 = s2.charAt(i);
            if(!chars.containsKey(ch1))chars.put(ch1, 0);
            if(!chars.containsKey(ch2))chars.put(ch2, 0);
            chars.merge(ch1, 1, Integer::sum);
            chars.merge(ch2, -1, Integer::sum);
        }
        for(int sum : chars.values()){
            if(sum != 0) return false;
        }
        return true;
    }
}
