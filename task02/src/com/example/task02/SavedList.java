package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.io.FileOutputStream;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private final File file;
    private List<E> eList;

    public SavedList(File file) {
        this.file = file;
        if(file.exists()){
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                this.eList = (List<E>) objectInputStream.readObject();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
        else{
            this.eList = new ArrayList<>();
        }
    }

    @Override
    public E get(int index) {
        return this.eList.get(index);
    }

    @Override
    public E set(int index, E element) {
        E result = this.eList.set(index, element);
        writeToFile();
        return result;
    }

    @Override
    public int size() {
        return eList.size();
    }

    @Override
    public void add(int index, E element) {
        eList.add(index, element);
        writeToFile();
    }

    @Override
    public E remove(int index) {
        E result = eList.remove(index);
        writeToFile();
        return result;
    }

    private void writeToFile() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(this.eList);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
