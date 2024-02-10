package com.example.task02;

import java.io.*;
import java.nio.file.Files;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.AbstractList;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private List<E> savedList = new ArrayList<>();
    private final File file;
    public SavedList(File file) {
        if (file.exists()){
            try (ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(file.toPath()))){
                this.savedList = (List<E>) objectInputStream.readObject();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        this.file = file;
    }

    private void writeFile() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(file.toPath()))){
            objectOutputStream.writeObject(this.savedList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public E get(int index) {
        return this.savedList.get(index);
    }

    @Override
    public E set(int index, E element) {
        E result = this.savedList.set(index, element);
        this.writeFile();
        return result;
    }

    @Override
    public int size() {
        return savedList.size();
    }

    @Override
    public void add(int index, E element) {
        savedList.add(index, element);
        this.writeFile();
    }

    @Override
    public E remove(int index) {
        E result = this.savedList.remove(index);
        this.writeFile();
        return result;
    }
}
