package com.example.task02;

import java.io.*;
import java.nio.file.Files;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private List<E> list;
    private File file;

    public SavedList(File file) {
        this.file = file;
        if(!file.exists()){
            list = new ArrayList<>();
        }
        else{
            listFromFile();
        }
    }

    private void writeToFile(){
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(Files.newOutputStream(file.toPath()))){
            objectOutputStream.writeObject(list);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void listFromFile(){
        try(ObjectInputStream objectInputStream = new ObjectInputStream(Files.newInputStream(file.toPath()))){
            list = (List<E>) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E elem = list.set(index, element);
        writeToFile();
        return elem;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index,element);
        writeToFile();
    }

    @Override
    public E remove(int index) {
        E elem = list.remove(index);
        writeToFile();
        return elem;
    }
}
