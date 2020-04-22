package com.example.task02;

import java.io.File;
import java.io.Serializable;
import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private final File backup;
    private ArrayList<E> list;

    public SavedList(File file) {
        backup = file;
        list = new ArrayList<>();
        if (file.exists())
            load();
    }

    private void doBackup() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(backup))) {
            out.writeObject(list);
        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }

    private void load() {
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(backup))) {
            list = (ArrayList<E>) in.readObject();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @Override
    public E get(int index) {

        return list.get(index);
    }

    @Override
    public E set(int index, E element) {

        E buffer = list.set(index, element);
        doBackup();
        return buffer;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        doBackup();
    }

    @Override
    public E remove(int index) {
        E buffer = list.remove(index);
        doBackup();
        return buffer;
    }
}