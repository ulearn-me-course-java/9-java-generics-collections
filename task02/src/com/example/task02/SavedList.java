package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private ArrayList<E> arrList;
    private File file;

    public SavedList(File file) {

        this.file = file;

        if (file.isFile()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                arrList = (ArrayList<E>) ois.readObject();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        } else {
            arrList = new ArrayList<>();
        }
    }

    private void serialize() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(arrList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public E get(int index) {
        return arrList.get(index);
    }

    @Override
    public E set(int index, E element) {
        E elem = arrList.set(index, element);
        serialize();
        return elem;
    }

    @Override
    public int size() {
        return arrList.size();
    }

    @Override
    public void add(int index, E element) {
        arrList.add(index, element);
    }

    @Override
    public E remove(int index) {
        E elem = arrList.remove(index);
        serialize();
        return elem;
    }
}
