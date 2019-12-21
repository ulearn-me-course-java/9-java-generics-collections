package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private List<E> files;
    private File onDiskCopy;

    public SavedList(File file) {
        this.onDiskCopy = file;
        if (file.exists()) {
            loadOnDiskCopy();
        } else {
            this.files = new ArrayList<>();
        }
    }

    private void saveOnDiskCopy() {
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(this.onDiskCopy));
            stream.writeObject(this.files);
            stream.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void loadOnDiskCopy() {
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(this.onDiskCopy));
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
        saveOnDiskCopy();
        return elem;
    }

    @Override
    public int size() {
        return this.files.size();
    }

    @Override
    public void add(int index, E element) {
        this.files.add(index, element);
        saveOnDiskCopy();
    }

    @Override
    public E remove(int index) {
        E indexRemove = this.files.get(index);
        this.files.remove(indexRemove);
        saveOnDiskCopy();
        return indexRemove;
    }
}
