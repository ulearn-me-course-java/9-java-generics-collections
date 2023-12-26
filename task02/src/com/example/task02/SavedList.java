package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private List<E> list = new ArrayList<E>();
    private final File file;
    public SavedList(File file) {
        if (file.exists()){
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                this.list = (List<E>) objectInputStream.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.file = file;
    }

    private void writeFile() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(this.file))) {
            objectOutputStream.writeObject(this.list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public E get(int index) {
        return this.list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E elem = this.list.set(index, element);
        this.writeFile();
        return elem;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        this.writeFile();
    }

    @Override
    public E remove(int index) {
        E elem = this.list.remove(index);
        this.writeFile();
        return elem;
    }
}
