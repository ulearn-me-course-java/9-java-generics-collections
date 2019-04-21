package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private List<E> list;
    private File file;

    SavedList(File file) {
        this.file = file;
        if (file.isFile()) {
            list = deserialize();
        } else {
            list = new ArrayList<>();
            serialize();
        }
    }

    private void serialize() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private List<E> deserialize() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<E>) ois.readObject();
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E e = list.set(index, element);
        serialize();
        return e;
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
        E e = list.remove(index);
        serialize();
        return e;
    }
}
