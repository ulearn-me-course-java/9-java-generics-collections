package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private List<E> files;
    private File diskCopy;

    public SavedList(File file) {
        this.diskCopy = file;
        if (file.exists()) {
            loadCopy();
        } else {
            this.files = new ArrayList<>();
        }
    }

    private void saveCopy() {
        try {
            ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(this.diskCopy));
            stream.writeObject(this.files);
            stream.close();
        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    private void loadCopy() {
        try {
            ObjectInputStream stream = new ObjectInputStream(new FileInputStream(this.diskCopy));
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
        E setElement = this.files.set(index, element);
        saveCopy();
        return setElement;
    }

    @Override
    public int size() {
        return this.files.size();
    }

    @Override
    public void add(int index, E element) {
        this.files.add(index, element);
        saveCopy();
    }

    @Override
    public E remove(int index) {
        E removeIndex = this.files.get(index);
        this.files.remove(removeIndex);
        saveCopy();
        return removeIndex;
    }
}
