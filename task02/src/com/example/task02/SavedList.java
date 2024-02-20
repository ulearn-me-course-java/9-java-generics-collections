package com.example.task02;

import java.io.File;
import java.io.Serializable;
import java.util.AbstractList;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private final File file;
    private List<E> list;
    public SavedList(File file) {
        if (!file.exists()) {
            this.list = new ArrayList<>();
        } else {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                this.list = (List<E>) objectInputStream.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.file = file;
    }

    private void writeFile() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.file))) {
            objectOutputStream.writeObject(this.list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public E get(int index) {
        return this.list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E elem = this.list.set(index, element);
        this.writeFile();
        return elem;
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public void add(int index, E element) {
        this.list.add(index, element);
        this.writeFile();
    }

    @Override
    public E remove(int index) {
        E element = this.list.remove(index);
        this.writeFile();
        return element;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (E elem : this.list) {
            sb.append(elem).append(" ");
        }
        return sb.toString();
    }
}
