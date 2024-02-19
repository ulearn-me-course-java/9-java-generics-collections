package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private List<E> elements;
    private File objectFile;

    public SavedList(File newFile) {
        if(!newFile.exists())
            elements = new ArrayList<>();
        else
            createList(newFile);

        objectFile = newFile;
    }

    private void createList(File newFile){
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(newFile))) {
            elements = (List<E>) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void writeList(){
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(objectFile))) {
            objectOutputStream.writeObject(elements);
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
        E changed = elements.set(index, element);

        writeList();

        return changed;
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void add(int index, E element) {
        elements.add(index, element);
        writeList();
    }

    @Override
    public E remove(int index) {
        E removed = elements.remove(index);
        writeList();
        return removed;
    }
}
