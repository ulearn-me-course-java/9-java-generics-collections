package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private final List<E> list;
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
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        list.set(index, element);
        serialize();
        return element;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        serialize();
    }

    @Override
    public E remove(int index) {
        E removed = list.get(index);
        list.remove(index);
        serialize();
        return removed;
    }

    private static <E> List<E> deserialize(InputStream inputStream) throws IOException {
        List<E> newlist = new ArrayList<>();
        try (ObjectInputStream ois = new ObjectInputStream(inputStream)) {
            newlist = (List<E>) ois.readObject();
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
