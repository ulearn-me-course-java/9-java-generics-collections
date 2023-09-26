package com.example.task02;

import java.io.File;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.AbstractList;
import java.util.ArrayList;

public class SavedList<E extends Serializable> extends AbstractList<E> {


    private ArrayList<E> list;
    private final File file;
    public SavedList(File file) {

        if (!file.exists()) {
            list = new ArrayList<>();
        } else {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(file.toPath()))) {
                list = (ArrayList<E>) objectInputStream.readObject();
            } catch (Exception ignored) {
            }
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
        list.add(index, element);
        saveToFile();
    }

    @Override
    public E remove(int index) {
        E element = list.remove(index);
        saveToFile();
        return element;
    }

    private void saveToFile() {
        try (ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(file.toPath()))) {
            outputStream.writeObject(list);
        } catch (Exception ignored) {
        }
    }
}
