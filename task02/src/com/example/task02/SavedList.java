package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<T> extends AbstractList<T> {

    private final List<T> list;
    private final File file;

    public SavedList(File file) throws IOException {
        this.file = file;
        if (file.exists()) {
            list = deserialize(new FileInputStream(file));
        } else {
            list = new ArrayList<>();
        }
    }

    @Override
    public T get(int index) {
        return list.get(index);
    }

    @Override
    public T set(int index, T element) {
        list.set(index, element);
        serialize();
        return element;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, T element) {
        list.add(index, element);
        serialize();
    }

    @Override
    public T remove(int index) {
        T removed = list.get(index);
        list.remove(index);
        serialize();
        return removed;
    }

    private static <T> List<T> deserialize(InputStream inputStream) throws IOException {
        List<T> newlist = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(inputStream)) {
            newlist = (List<T>) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return newlist;
    }

    private void serialize() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
