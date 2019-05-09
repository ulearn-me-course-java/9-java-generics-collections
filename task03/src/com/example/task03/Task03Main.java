package com.example.task03;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Array;
import java.nio.charset.Charset;
import java.util.*;
import java.util.regex.Pattern;


public class Task03Main {

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        List<Set<String>> answer = new ArrayList<>();
        Comparator<TreeSet<String>> comparator = Comparator.comparing(obj -> obj.first());
        ArrayList<TreeSet<String>> array = new ArrayList<>();
        Scanner scanner = new Scanner(inputStream);

        while (scanner.hasNext()) {
            String tempStr = scanner.next().toLowerCase();


            if (Pattern.matches(".*\\p{InCyrillic}.*", tempStr) && !tempStr.contains("-") && tempStr.length() >= 3) {
                if(!findSet(array, tempStr)){
                    TreeSet<String> temp = new TreeSet<>();
                    temp.add(tempStr);
                    array.add(temp);
                }
            }
        }

        Collections.sort(array,comparator);

        for (TreeSet<String> set : array) {
            if (set.size() >= 2) {
                answer.add(set);
            }
        }


        return answer;
    }


    private static boolean findSet( ArrayList<TreeSet<String>> list, String string) {
        for (TreeSet<String> set : list) {
            String firstSetEl = (String) set.toArray()[0];
            if (getHashCode(firstSetEl) == getHashCode(string)) {
                set.add(string);
                return true;
            }
        }
        return false;
    }

    private static int getHashCode(String string) {
        //Хэш код одинаков для анаграмм.
        int hashCode = 0;
        for (char ch : string.toCharArray()) {
            hashCode += Objects.hashCode(ch);
        }
        return hashCode;
    }
}
