package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    ArrayList<E> savedList;
    File file;

    public SavedList(File file) {
        this.file = file;
        savedList = new ArrayList<>();
        if (file.exists()) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
                savedList = (ArrayList<E>) inputStream.readObject();
            } catch (Exception e) {

            }
        }
    }

    public E get(int index) {
        return savedList.get(index);
    }

    public E set(int index, E element) {
        E oldElement = savedList.set(index, element);
        save();
        return oldElement;
    }

    public int size() {
        return savedList.size();
    }

    public void add(int index, E element) {
        savedList.add(index, element);
        save();
    }

    public E remove(int index) {
        E oldElement = savedList.remove(index);
        save();
        return oldElement;
    }

    private void save() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(savedList);
        } catch (Exception e) {

        }
    }
}