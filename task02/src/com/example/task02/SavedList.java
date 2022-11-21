package com.example.task02;

import java.io.*;
import java.nio.file.Files;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private List<E> elements;
    private File file;

    public SavedList(File file) {
        if (!file.exists()) {
            elements = new ArrayList<>();
        }
        else {
            try (ObjectInputStream ois = new ObjectInputStream(Files.newInputStream(file.toPath()))) {
                elements = (List<E>) ois.readObject();
            }
            catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.file = file;
    }

    private void writeList() {
        try (ObjectOutputStream ous = new ObjectOutputStream(Files.newOutputStream(file.toPath()))) {
            ous.writeObject(elements);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public E get(int index) {
        return elements.get(index);
    }

    @Override
    public E set(int index, E element) {
        E elem = elements.set(index,element);
        writeList();
        return elem;
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void add(int index, E element) {
        elements.add(index,element);
        writeList();
    }

    @Override
    public E remove(int index) {
        E element = elements.remove(index);
        writeList();
        return element;
    }
}
