package com.example.task02;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.Serializable;
import java.util.AbstractList;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private List<E> elements;
    private File file;

    public SavedList(File file) {
        this.file = file;
        this.elements = loadListFromFile();
    }

    private List<E> loadListFromFile() {
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                return (List<E>) ois.readObject();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return new ArrayList<>();
    }

    private void saveListToFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(elements);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public E get(int index) {
        return elements.get(index);
    }

    @Override
    public E set(int index, E element) {
        E previousElement = elements.set(index, element);
        saveListToFile();
        return previousElement;
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void add(int index, E element) {
        elements.add(index, element);
        saveListToFile();
    }

    @Override
    public E remove(int index) {
        E removedElement = elements.remove(index);
        saveListToFile();
        return removedElement;
    }
}