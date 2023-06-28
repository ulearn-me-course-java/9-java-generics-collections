package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private List<E> data;
    private File file;
    public SavedList(File file) {
        this.file = file;
        if (!file.exists()){
            this.data = new LinkedList<>();
        }else {
            data = desialization();
        }
    }
    private List<E> desialization(){
        try {
            FileInputStream fileInputStream = new FileInputStream(file);
            ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);
            List<E> temp = (List<E>) objectInputStream.readObject();
            objectInputStream.close();
            return temp;
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }
    private void serialization(){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
            objectOutputStream.writeObject(data);
            objectOutputStream.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public E get(int index) {
        return data.get(index);
    }
    @Override
    public E set(int index, E element) {
        E result = data.set(index, element);
        serialization();
        return result;
    }
    @Override
    public int size() {
        return data.size();
    }
    @Override
    public void add(int index, E element) {
        data.add(index,element);
        serialization();
    }
    @Override
    public E remove(int index) {
        E var = data.remove(index);
        serialization();
        return null;
    }

}
