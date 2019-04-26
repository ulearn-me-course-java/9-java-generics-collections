package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private File file;
    private List<E> list;

    public SavedList(File file) {
        try {
            if (!file.createNewFile()){
                try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))){
                    this.file = file;
                    this.list = (List<E>)inputStream.readObject();
                }
            }
            else {
                this.file = file;
                this.list = new ArrayList<>();
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E elem = list.get(index);
        list.set(index, element );
        rewriteFile();
        return elem;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
    }

    @Override
    public E remove(int index) {
        E elem = list.get(index);
        list.remove(index);
        rewriteFile();
        return elem;
    }

    private void rewriteFile(){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))){
            outputStream.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}