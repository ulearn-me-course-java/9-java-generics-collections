package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private List<E> list = new ArrayList<>();
    private final File file;

    public SavedList(File file) {
        this.file = file;
        if (file.exists()) {
            load();
        } else {
            try {
                file.createNewFile();
            } catch (Exception e) {
                throw new RuntimeException("File can't be created", e);
            }
        }
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E lastEl = list.get(index);
        list.set(index, element);
        save();
        return lastEl;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        save();
    }

    @Override
    public E remove(int index) {
        E removedElement = list.remove(index);
        save();
        return removedElement;
    }

    @Override
    public String toString() {
        return list.toString();
    }

    private void save() {
        ObjectOutputStream stream;
        try {
            stream = new ObjectOutputStream(new FileOutputStream(file));
            stream.writeObject(list);
            stream.close();
        } catch (Exception e) {
            throw new RuntimeException("List saving is not possible", e);
        }
    }

    private void load() {
        ObjectInputStream stream;
        try {
            stream = new ObjectInputStream(new FileInputStream(file));
            list = (ArrayList<E>) stream.readObject();
            stream.close();
        } catch (Exception e) {
            throw new RuntimeException("File reading is not possible", e);
        }
    }
}
