package com.example.task02;

import java.io.File;
import java.io.IOException;

public class Task02Main {

    public static void main(String[] args) {

        File file = new File("./testlist.dat");

        file.delete();
        SavedList<String> list1 = new SavedList<>(file);
        list1.add("Z");
        System.out.println("list1: " + list1);

        SavedList<String> list2 = new SavedList<>(file);
        System.out.println("list2: " + list2);
        list2.add("B");
        list2.set(0, "A");
        System.out.println("list2: " + list2);
        list2.remove(1);
        SavedList<String> list3 = new SavedList<>(file);
        System.out.println("list3: " + list3);
    }

}
