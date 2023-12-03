package com.example.task03;

import java.io.*;
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

    public static List<Set<String>> findAnagrams(InputStream inputStream, Charset charset)
    {
        ArrayList<Set<String>> output = new ArrayList<Set<String>>();
        try (BufferedReader isr = new BufferedReader(new InputStreamReader(inputStream, charset)))
        {
            String word;
            while ((word = isr.readLine()) != null) {
                word = word.toLowerCase();
                if((word.length()>3))
                {
                    if(word.matches("[а-я]+"))
                    {
                        Boolean added = false;
                        for(int list_i = 0; list_i < output.size(); list_i++ )
                        {
                            Set<String> currentSet = output.get(list_i);
                            for(String setElem : currentSet)
                            {
                                if(wordComp(setElem,word))
                                {
                                    currentSet.add(word);
                                    output.set(list_i, currentSet);
                                    added = true;
                                }
                                if(added)
                                    break;
                            }
                            if(added)
                                break;
                        }
                        if(!added)
                        {
                            Set<String> setToAdd = new HashSet<String>();
                            setToAdd.add(word);
                            output.add(setToAdd);
                        }

                    }
                }
            }
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        for(int list_i = 0; list_i < output.size(); list_i++ )
        {
            output.set(list_i,new TreeSet<String>(output.get(list_i)));
        }
        output = QuickSort(output);
        for(int list_i = 0; list_i < output.size(); list_i++ )
        {
                if(output.get(list_i).size()<2)
                {
                    output.remove(list_i);
                    list_i--;
                }
        }
        return output;
    }

    public static boolean wordComp(String word, String elem)
    {
        char[] charWord = word.toCharArray();
        char[] charElem = elem.toCharArray();
        Arrays.sort(charWord);
        Arrays.sort(charElem);
        return Arrays.equals(charWord, charElem);
    }

    public static ArrayList<Set<String>> QuickSort(ArrayList<Set<String>> array)
    {
        return QuickSort(array, 0, array.size()-1);
    }
    public static ArrayList<Set<String>> QuickSort(ArrayList<Set<String>> array, int minIndex, int maxIndex)
    {
        if (minIndex >= maxIndex)
        {
            return array;
        }
        int pivotIndex = Partition(array, minIndex, maxIndex);
        QuickSort(array, minIndex, pivotIndex - 1);
        QuickSort(array, pivotIndex + 1, maxIndex);
        return array;
    }
    public static int Partition(ArrayList<Set<String>> array, int minIndex, int maxIndex)
    {
        int pivot = minIndex - 1;
        for (int i = minIndex; i < maxIndex; i++)
        {
            String candOne = (new TreeSet<String>(array.get(i))).first();
            String candTwo = (new TreeSet<String>(array.get(maxIndex))).first();
            if (candTwo.compareTo(candOne)>0)
            {
                pivot++;
                //swap
                Set<String> pivotElem = array.get(pivot);
                Set<String> iElem = array.get(i);
                array.set(i, pivotElem);
                array.set(pivot, iElem);
                //swap
            }
        }

        pivot++;
        //swap
        Set<String> pivotElem = array.get(pivot);
        Set<String> iElem = array.get(maxIndex);
        array.set(maxIndex, pivotElem);
        array.set(pivot, iElem);
        //swap
        return pivot;
    }
}
