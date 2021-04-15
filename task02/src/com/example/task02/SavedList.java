package com.example.task02;

import java.io.*;
import java.nio.file.Files;
import java.util.AbstractList;
import java.util.ArrayList;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private ArrayList<E> elements;
    private final File file;

    public SavedList(File file) {
        this.file = file;
        if (!openFile()) elements = new ArrayList<>();
    }

    @Override
    public E get(int index) {
        return elements.get(index);
    }

    @Override
    public E set(int index, E element) {
        E value = elements.set(index, element);
        saveFile();
        return value;
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void add(int index, E element) {
        elements.add(index, element);
        saveFile();
    }

    @Override
    public E remove(int index) {
        E value = elements.remove(index);
        saveFile();
        return value;
    }

    private void saveFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(file.toPath()))) {
            oos.writeObject(elements);
        } catch (IOException ignored) {
        }
    }

    private boolean openFile() {
        try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(file.toPath()))) {
            this.elements = (ArrayList<E>) ois.readObject();
            return true;
        } catch (IOException | ClassNotFoundException ioException) {
            return false;
        }
    }
}
