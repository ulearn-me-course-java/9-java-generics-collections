package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private List<E> fileList = new ArrayList<>();
    private File file;

    public SavedList(File file) {
        this.file = file;
        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.file))) {
                fileList = (List<E>) ois.readObject();
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public E get(int index) {
        return fileList.get(index);
    }

    @Override
    public E set(int index, E element) {
        E e = fileList.set(index, element);
        updateFile();
        return e;
    }

    @Override
    public int size() {
        return fileList.size();
    }

    @Override
    public void add(int index, E element) {
        fileList.add(index, element);
        updateFile();
    }

    @Override
    public E remove(int index) {
        E e = fileList.remove(index);
        updateFile();
        return e;
    }

    private void updateFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.file))) {
            oos.writeObject(fileList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
