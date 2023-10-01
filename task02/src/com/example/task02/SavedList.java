package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private final File saveFile;
    private ArrayList<E> elementsList;

    public SavedList(File file) {
        saveFile = file;
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            elementsList = (ArrayList<E>) objectInputStream.readObject();
        } catch (java.io.IOException e) {
            elementsList = new ArrayList<>();
        } catch (java.lang.ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public E get(int index) {
        return elementsList.get(index);
    }

    @Override
    public E set(int index, E element) {
        E oldElement = elementsList.set(index, element);
        saveElementsList();
        return oldElement;
    }

    @Override
    public int size() {
        return elementsList.size();
    }

    @Override
    public void add(int index, E element) {
        elementsList.add(index, element);
        saveElementsList();
    }

    @Override
    public E remove(int index) {
        E oldElement = elementsList.remove(index);
        saveElementsList();
        return oldElement;
    }

    private void saveElementsList() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(saveFile))) {
            objectOutputStream.writeObject(elementsList);
        } catch (java.io.IOException e) {
            e.printStackTrace();
        }
    }
}
