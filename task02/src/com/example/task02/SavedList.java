package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private List<E> elements = new ArrayList<>();
    private File file;

    public SavedList(File file) {
        this.file = file;
        if (file.exists()) {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                this.elements = (List<E>) objectInputStream.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public E get(int index) {
        return elements.get(index);
    }

    @Override
    public E set(int index, E element) {
        E exElement = elements.set(index, element);
        writeFile();
        return exElement;
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void add(int index, E element) {
        elements.add(index, element);
        writeFile();
    }

    private void writeFile() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(elements);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public E remove(int index) {
        E exElement = elements.remove(index);
        writeFile();
        return exElement;
    }
}