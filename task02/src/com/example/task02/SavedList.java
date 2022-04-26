package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private List<E> list;
    private final File file;

    public SavedList(File file) {
        this.file = file;
        if (file.exists()) {
            try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))){
                list = (ArrayList<E>) objectInputStream.readObject();
            } catch (Exception exception){
                exception.printStackTrace();
            }
        } else{
            list = new ArrayList<>();
        }
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E value = list.set(index, element);
        writeToFile();
        return value;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        writeToFile();
    }

    @Override
    public E remove(int index) {
        E value = list.remove(index);
        writeToFile();
        return value;
    }

    private void writeToFile(){
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))){
            objectOutputStream.writeObject(list);
        } catch (Exception exception){
            exception.printStackTrace();
        }
    }
}
