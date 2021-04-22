package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private ArrayList<E> sl;
    private final File file;

    public SavedList(File file) {
        this.file = file;
        sl = new ArrayList<>();

        if (file.exists()) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
                sl = (ArrayList<E>) inputStream.readObject();
            } catch (IOException | ClassNotFoundException ioException) {
                ioException.printStackTrace();
            }
        }
    }

    private void save() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(sl);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
    @Override
    public E get(int index) {
        return sl.get(index);
    }

    @Override
    public E set(int index, E element) {
        E oe = sl.set(index,element);
        save();
        return oe;
    }

    @Override
    public int size() {
        return sl.size();
    }

    @Override
    public void add(int index, E element) {
        sl.add(index,element);
        save();
    }

    @Override
    public E remove(int index) {
        E de = sl.remove(index);
        save();
        return de;
    }
}
