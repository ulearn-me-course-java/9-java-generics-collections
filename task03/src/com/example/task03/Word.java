package com.example.task03;

import java.util.ArrayList;
import java.util.Objects;

public class Word {

    private static final int SIZE = 32;

    String word;

    ArrayList<Integer> table;

    public Word(String word) {
        this.word = word;

        computeTable(word);
    }

    private void computeTable(String word) {
        ArrayList<Integer> arrayList = new ArrayList<>(SIZE);

        final char firstLetterNum = Task03Main.start;

        char letter = firstLetterNum;
        for (int i = 0; i < SIZE; ++i) {
            arrayList.add(0);
            letter++;
        }

        char[] charArray = word.toCharArray();
        for (char var : charArray) {
            int index = var - firstLetterNum;
            Integer count = arrayList.get(index);
            arrayList.set(index, count + 1);
        }

        this.table = arrayList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Word word = (Word) o;
/*
        for (int i = 0; i < table.size(); ++i) {
            if (table.get(i) != word.table.get(i)) {
                return false;
            }
        }*/

        return table.equals(word.table);

    }

    @Override
    public int hashCode() {
        return Objects.hash(table.toArray());
    }
}
