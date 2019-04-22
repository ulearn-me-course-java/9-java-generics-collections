package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private List<E> eList;
    private File file;

    public SavedList(File file) {
        eList = new ArrayList<E>();
        this.file = file;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            ArrayList<E> temp = (ArrayList<E>) ois.readObject();
            eList = temp;
        } catch (Exception e) { }
    }

    private void reload() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(eList);
        } catch (Exception ex) { }
    }

    @Override
    public E get(int index) {
        return eList.get(index);
    }

    @Override
    public E set(int index, E element) {
        E res = eList.set(index, element);//return previous
        reload();
        return res;
    }

    @Override
    public int size() {
        return eList.size();
    }

    @Override
    public void add(int index, E element) {
        eList.add(index, element);
        reload();
    }

    @Override
    public E remove(int index) {
        E res = eList.remove(index);//return removed element
        reload();
        return res;
    }
}