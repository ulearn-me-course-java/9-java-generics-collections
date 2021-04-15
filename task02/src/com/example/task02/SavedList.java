package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private List<E> savedList;
    private final File file;

    public SavedList(File file) {
        this.savedList = new ArrayList<>();
        this.file = file;
        {
            if (file.exists()) {
                try (ObjectInputStream objIn = new ObjectInputStream(new FileInputStream(file))) {
                    try {
                        savedList = (ArrayList<E>)objIn.readObject();
                    } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                    }
                } catch (IOException ex) {
                    System.out.println("IO Exception!");
                }
            }
        }
    }

    @Override
    public E get(int index) {
        return savedList.get(index);
    }

    @Override
    public E set(int index, E element) {
        E elem = savedList.set(index, element);
        renewFile();
        return elem;
    }

    @Override
    public int size() {
        return savedList.size();
    }

    @Override
    public void add(int index, E element) {
        savedList.add(index, element);
        renewFile();
    }

    @Override
    public E remove(int index) {
        E elem = savedList.remove(index);
        renewFile();
        return elem;
    }

    private void renewFile() {
        try (ObjectOutputStream objOut = new ObjectOutputStream(new FileOutputStream(this.file))){
            objOut.writeObject(savedList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
