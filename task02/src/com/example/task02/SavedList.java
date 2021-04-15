package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private List<E> list;
    private final File file;

    public SavedList(File file) {
        this.file = file;
        list = new ArrayList<>();

        if (file.exists()) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
                list = (ArrayList<E>) inputStream.readObject();
            } catch (Exception ignored) { }
        }
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E el = list.set(index, element);
        updateFile();
        return el;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        updateFile();
    }

    @Override
    public E remove(int index) {
        E el = list.remove(index);
        updateFile();
        return el;
    }

    private void updateFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(list);
        } catch (Exception ignored) { }
    }
}
