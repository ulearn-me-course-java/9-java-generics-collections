package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private ArrayList<E> list;
    private File file;
    public SavedList(File file) {

        if (file.exists()) {

            try(ObjectInputStream imputStream = new ObjectInputStream(new FileInputStream(file))) {
                this.list = (ArrayList<E>) imputStream.readObject();

            } catch (Exception e) {

            }
        } else {
                this.list = new ArrayList<>();
        }
        this.file = file;
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E value = list.set(index, element);
        saveToFile();
        return value;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        this.list.add(index, element);
        this.saveToFile();
    }

    @Override
    public E remove(int index) {
        E element = this.list.remove(index);
        this.saveToFile();
        return element;
    }

    private void saveToFile(){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            outputStream.writeObject(this.list);
        } catch (Exception e) {

        }
    }
}
