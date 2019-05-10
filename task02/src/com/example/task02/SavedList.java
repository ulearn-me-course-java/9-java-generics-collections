package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private List<E> array;
    private File file;

    public SavedList(File file) throws ClassNotFoundException {

        this.file = file;

        if (!file.exists()) {
            array = new ArrayList<>();
            return;
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {

            array = (ArrayList<E>) ois.readObject();

        } catch (IOException e) {
            e.getStackTrace();
        }
    }

    @Override
    public E get(int index) {
        return array.get(index);
    }

    @Override
    public E set(int index, E element) {
        E value = array.get(index);

        array.set(index, element);
        save();

        return value;
    }

    @Override
    public int size() {
        return array.size();
    }

    @Override
    public void add(int index, E element) {
        array.add(index, element);
        save();
    }

    @Override
    public E remove(int index) {
        E value = array.get(index);

        array.remove(index);
        save();

        return value;
    }

    private void save() {
        try {
            if (!file.exists())
                file.createNewFile();

            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(array);

            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.getStackTrace();
        }
    }
}