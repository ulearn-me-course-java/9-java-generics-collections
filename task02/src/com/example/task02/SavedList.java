package com.example.task02;

import java.io.*;
import java.io.File;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private final File file;
    private List<E> listElements;
    public SavedList(File file) {
        if (!file.exists()) {
            this.listElements = new ArrayList<>();
        } else {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                this.listElements = (List<E>) objectInputStream.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.file = file;
    }

    private void writeFile() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.file))) {
            objectOutputStream.writeObject(this.listElements);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public E get(int index) {
        return this.listElements.get(index);
    }

    @Override
    public E set(int index, E element) {
        E elem = this.listElements.set(index, element);
        this.writeFile();
        return elem;
    }

    @Override
    public int size() {
        return this.listElements.size();
    }

    @Override
    public void add(int index, E element) {
        this.listElements.add(index, element);
        this.writeFile();
    }

    @Override
    public E remove(int index) {
        E element = this.listElements.remove(index);
        this.writeFile();
        return element;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (E elem : this.listElements) {
            sb.append(elem).append(" ");
        }
        return sb.toString();
    }
}
