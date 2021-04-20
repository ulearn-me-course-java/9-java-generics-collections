package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private ArrayList<E> list;
    private final File file;

    public SavedList(File file) {
        this.file = file;
        list = new ArrayList<>();
        if(file.exists()){
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))){
                list = (ArrayList<E>) inputStream.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E oldEl = list.set(index, element);
        saveList();
        return oldEl;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        saveList();
    }

    @Override
    public E remove(int index) {
        E element = list.remove(index);
        saveList();
        return element;
    }

    private void saveList() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(list);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}
