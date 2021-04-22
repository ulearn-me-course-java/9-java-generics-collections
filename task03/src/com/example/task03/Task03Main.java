package com.example.task03;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.*;

public class Task03Main {

    public static void main(String[] args) throws IOException {

        List<Set<String>> anagrams = findAnagrams(new FileInputStream("task03/resources/singular.txt"), StandardCharsets.ISO_8859_1);
        //for (Set<String> anagram : anagrams) {
        //    System.out.println(anagram);
        //}

        System.out.println(anagrams);

    }

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset) {
        Scanner scan = new Scanner(inputStream, charset.name());
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset));
        List<String> l = new ArrayList<>();
        List<Set<String>> lResult = new ArrayList<>();
        String s = "";

        List<String> result = new ArrayList<>();

        while (scan.hasNext()) {
            l.add(scan.nextLine().toLowerCase());
        }
        do {
            boolean b1 = true;
            String w1 = l.remove(0);
            SortedSet<String> ss1 = new TreeSet<>();
            ss1.add(w1);
            int l1 = w1.length();
            char[] c1 = w1.toCharArray();

            if (l1 <= 3) { continue; }

            for (int sym = 0; sym < l1; sym++) {
                if (c1[sym] <= 'A' || c1[sym] >= 'z') {
                    b1 = false;
                    break;
                }
            }

            if (!b1) {
                continue;
            }

            HashMap<Character,Integer> cl1 = directionCharer(w1.toCharArray(),l1);

            for (int count1 = 0; count1 < l.size();) {
                String w2 = l.get(count1);
                int l2 = w2.length();

                if (w1.equals(w2) || l2 <= 3) {
                    l.remove(count1);
                } else {
                    HashMap<Character,Integer> cl2 = directionCharer(w2.toCharArray(),l2);
                    if (cl1.equals(cl2)) {
                        ss1.add(l.remove(count1));
                    }
                    else { count1++; }
                }
            }
            if (ss1.size() > 1) {
                lResult.add(ss1);
            }
        } while (l.size() > 0);
        return lResult;
    }
    private static HashMap<Character,Integer> directionCharer(char[] c, int l) {
        HashMap<Character,Integer> cl = new HashMap<>();
        for (int count = 0; count < l; count++) {
            if (!cl.containsKey(c[count])) {
                cl.put(c[count],1);
            } else { cl.put(c[count],cl.get(c[count]) + 1); }
        }
        return cl;
    }
    public static void prov(InputStream inputStream, Charset charset) {
        Scanner scan = new Scanner(inputStream, charset.name());
        BufferedReader br = new BufferedReader(new InputStreamReader(inputStream, charset));
        List<String> l = new ArrayList<>();
        List<Set<String>> lResult = new ArrayList<>();
        String s = "";

        List<String> result = new ArrayList<>();

        while (scan.hasNext()) {
            l.add(scan.nextLine().toLowerCase());
        }

        int index = 0, count = 0;

        System.out.println(l.size());

        do {
            String w1 = l.remove(0);
            SortedSet<String> ss1 = new TreeSet<>();
            ss1.add(w1);
            int l1 = w1.length();

            if (l1 <= 3) { continue; }

            char[] c1 = w1.toCharArray();
            HashMap<Character,Integer> cl1 = directionCharer(c1,l1);

            System.out.println(w1 + " - Parse word");

            boolean succes = true;

            for (int count1 = 0; count1 < l.size();) {
                String w2 = l.get(count1);
                int l2 = w2.length();

                if (w1.equals(w2) || l2 <= 3) {
                    l.remove(count1);
                } else {
                    HashMap<Character,Integer> cl2 = directionCharer(w2.toCharArray(),l2);
                    if (cl1.equals(cl2)) {
                        ss1.add(l.remove(count1));
                    }
                    else { count1++; }
                }
            }
            index++;
            if (ss1.size() > 0) {
                lResult.add(ss1);
            }
        } while (l.size() > 0);

        System.out.println(count + " - num word");
        System.out.println(lResult.size() + " - set num");
        System.out.println("-----");
        System.out.println(lResult.get(1));
    }
}
