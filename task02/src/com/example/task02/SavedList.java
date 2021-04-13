package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private List<E> list;
    private final File file;

    public SavedList(File file) {
        list = new ArrayList<>();
        this.file = file;
        if (file.exists()) {
            try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
                list = (ArrayList<E>) inputStream.readObject();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(list);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E set = list.set(index, element);
        update();
        return set;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        update();
    }

    @Override
    public E remove(int index) {
        E remove = list.remove(index);
        update();
        return remove;
    }
}
