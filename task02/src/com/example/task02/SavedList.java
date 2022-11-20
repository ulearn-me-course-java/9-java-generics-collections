package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private final File file;
    private List<E> list;

    public SavedList(File file) {
        if(!file.exists()){
            this.list = new ArrayList<>();
        } else {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                this.list = (List<E>) objectInputStream.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.file = file;
    }

    public void writeToFile(){
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(list);
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
        E elem = list.set(index, element);
        writeToFile();
        return elem;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        writeToFile();
    }

    @Override
    public E remove(int index) {
        E elem = list.remove(index);
        writeToFile();
        return elem;
    }
}
