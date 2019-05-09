package com.example.task02;

import java.util.AbstractList;
import java.io.*;
import java.util.ArrayList;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private ArrayList<E> list = new ArrayList<>();
    private File file;

    public SavedList(File file) {
        this.file = file;
        try {
            if (file.exists()) {
                try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))) {
                    this.file = file;
                    this.list = (ArrayList<E>) inputStream.readObject();
                }
            } else {
                this.file = file;
                this.list = new ArrayList<>();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E el = list.set(index, element);
        rewriteFile();
        return el;
    }

    private void rewriteFile(){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))){
            outputStream.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        rewriteFile();
    }

    @Override
    public E remove(int index) {
        E el = list.remove(index);
        rewriteFile();
        return el;
    }
}
