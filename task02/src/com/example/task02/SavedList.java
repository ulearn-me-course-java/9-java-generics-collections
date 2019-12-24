package com.example.task02;

import java.io.File;
import java.io.Serializable;
import java.util.AbstractList;
import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private List<E> files;
    private File ondiskCopy;

    public SavedList(File file) {
        this.ondiskCopy = file;
        if (file.exists()) {
            loadOndiskCopy();
        } else {
            this.files = new ArrayList<>();
        }
    }

    private void saveOndiskCopy() {
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(this.ondiskCopy));
            stream.writeObject(this.files);
            stream.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void loadOndiskCopy() {
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(this.ondiskCopy));
            this.files = (ArrayList<E>) stream.readObject();
            stream.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    @Override
    public E get(int index) {
        return this.files.get(index);
    }

    @Override
    public E set(int index, E element) {
        E elem = this.files.set(index, element);
        saveOndiskCopy();
        return elem;
    }

    @Override
    public int size() {
        return this.files.size();
    }

    @Override
    public void add(int index, E element) {
        this.files.add(index, element);
        saveOndiskCopy();
    }

    @Override
    public E remove(int index) {
        E indexRemove = this.files.get(index);
        this.files.remove(indexRemove);
        saveOndiskCopy();
        return indexRemove;
    }
}
