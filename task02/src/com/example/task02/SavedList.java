package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private ArrayList<E> list;
    private final File file;

    public SavedList(File file) {
        this.file = file;
        list = new ArrayList<>();
        if (file.exists()){
            load();
        }
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E el = list.set(index,element);
        save();
        return el;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        save();
    }

    @Override
    public E remove(int index) {
        E element = list.remove(index);
        save();
        return element;
    }

    private void save(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void load(){
        try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
            list = (ArrayList<E>)inputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
