package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;

public class SavedList<E extends Serializable> extends AbstractList<E> implements Serializable{
    ArrayList<E> savedList;
    File file;

    public SavedList(File file) {
        if (!file.exists()) {
            savedList = new ArrayList<>();
            try {
                file.createNewFile();
            } catch (IOException e) {
                //ignore
            }
        } else {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))){
                savedList = ((SavedList<E>) objectInputStream.readObject()).savedList;
            } catch (IOException | ClassNotFoundException exception) {
                //ignore
            }
        }
        this.file = file;
    }

    private void update() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))){
            out.writeObject(this);
        } catch (IOException exception) {
            //ignore
        }
    }

    @Override
    public E get(int index) {
        return savedList.get(index);
    }

    @Override
    public E set(int index, E element) {
        E el = savedList.set(index, element);
        update();
        return el;
    }

    @Override
    public int size() {
        return savedList.size();
    }

    @Override
    public void add(int index, E element) {
        savedList.add(index, element);
        update();
    }

    @Override
    public E remove(int index) {
        E el = savedList.remove(index);
        update();
        return el;
    }
}
