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

        if (file.exists()) {
            try (ObjectInputStream obj = new ObjectInputStream(new FileInputStream(file))) {
                list = (ArrayList<E>) obj.readObject();
            } catch (ClassNotFoundException | IOException e) {
                e.printStackTrace();
            }
        }
        else {
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
        saveFile();
        return value;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        saveFile();
    }

    @Override
    public E remove(int index) {
        E value = list.remove(index);
        saveFile();
        return value;
    }
    private void saveFile() {
        try (ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(file))){
            obj.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
