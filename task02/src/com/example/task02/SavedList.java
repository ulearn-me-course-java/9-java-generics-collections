package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private List<E> listInMemory = new ArrayList<>();
    private File file;

    public SavedList(File file) {
        this.file = file;
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.file))) {
                listInMemory = (List<E>) ois.readObject();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public E get(int index) {
        return listInMemory.get(index);
    }

    @Override
    public E set(int index, E element) {
        E e = listInMemory.set(index, element);
        updateList();
        return e;
    }

    @Override
    public int size() {
        return listInMemory.size();
    }

    @Override
    public void add(int index, E element) {
        listInMemory.add(index, element);
        updateList();
    }

    @Override
    public E remove(int index) {
        E e = listInMemory.remove(index);
        updateList();
        return e;
    }

    private void updateList() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.file))) {
            oos.writeObject(listInMemory);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }
}
