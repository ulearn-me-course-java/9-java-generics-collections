package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private final File file;
    private List<E> list;

    public SavedList(File file) {
        this.file = file;

        if (file.exists()){
            try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
                this.list = (List<E>) stream.readObject();
            } catch (ClassNotFoundException | IOException e){
                e.printStackTrace();
            }
        } else {
            this.list = new ArrayList<>();
        }
    }

    @Override
    public E get(int index) {
        return this.list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E value = this.list.set(index, element);
        writeToFile();
        return value;
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public void add(int index, E element) {
        this.list.add(index, element);
        writeToFile();
    }

    @Override
    public E remove(int index) {
        E value = this.list.remove(index);
        writeToFile();
        return value;
    }

    private void writeToFile(){
        try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(this.file))) {
            stream.writeObject(this.list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
