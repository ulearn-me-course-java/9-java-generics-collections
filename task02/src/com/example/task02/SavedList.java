package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    ArrayList<E> list = new ArrayList<>();

    File file;

    public SavedList(File file) {

        this.file = file;

        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException("Can't create new file :" + e.getMessage());
            }
        } else {
            readList();
        }
    }

    private void readList() {

        try (FileInputStream fileIn = new FileInputStream(file);
             ObjectInputStream objectIn = new ObjectInputStream(fileIn)) {

            Object curObj;

            while ((curObj = objectIn.readObject()) != null) {
                list.add((E) curObj);
            }
        }
        catch (EOFException e) {
            return;
        }
        catch (IOException e) {
            throw new RuntimeException("Can't read file : " + e.getMessage());
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("Unknown or wrong object in file : " + e.getMessage());
        }
    }

    private void updateFile() {
        try (FileOutputStream fileOut = new FileOutputStream(file);
             ObjectOutputStream objectOut = new ObjectOutputStream(fileOut)) {

            for (E element : list) {
                objectOut.writeObject(element);
            }

        } catch (IOException e) {
            throw new RuntimeException("Can't read file : " + e.getMessage());
        }
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E newElement = list.set(index, element);
        updateFile();
        return newElement;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        updateFile();
    }

    @Override
    public E remove(int index) {
        E removedElement = list.remove(index);
        updateFile();
        return removedElement;
    }
}
