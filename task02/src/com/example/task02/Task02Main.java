package com.example.task02;

import java.io.File;
import java.io.IOException;

public class Task02Main {

    public static void main(String[] args) throws IOException {

        File file = new File("./testlist.dat");

        SavedList<String> list1 = new SavedList<>(file);
        list1.add("Z");
        System.out.println("list1: " + list1);

        SavedList<String> list2 = new SavedList<>(file);
        System.out.println("list2: " + list2);

        SavedList<Integer> list3 = new SavedList<>(file);
        list3.add(1);
        list3.add(2);
        list3.add(3);
        list3.set(0, 999);
        System.out.println("list3: " + list3);

    }

}
