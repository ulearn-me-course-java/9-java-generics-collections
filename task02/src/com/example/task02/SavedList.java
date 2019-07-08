package com.example.task02;

import java.io.*;
import java.util.*;

public class SavedList<E extends Serializable> extends AbstractList<E> implements Serializable{
    ArrayList<E> savedList;
    File file;

    public SavedList(File file) {
        if (!file.exists()) {
            savedList = new ArrayList<>();
            try {
                file.createNewFile();
            } catch (IOException e) {
            }
        } else {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
                savedList = ((SavedList<E>) ois.readObject()).savedList;
            } catch (IOException | ClassNotFoundException exception) {
            }
        }
        this.file = file;
    }

    @Override
    public E get(int index) {
        return savedList.get(index);
    }

    @Override
    public E set(int index, E element) {
        E oldValue = savedList.set(index, element);
        update();
        return oldValue;
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
        E oldValue = savedList.remove(index);
        update();
        return oldValue;
    }

    private void update() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))){
            oos.writeObject(this);
        } catch (IOException exception) {

        }
    }
}
