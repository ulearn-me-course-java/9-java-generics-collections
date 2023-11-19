package com.example.task02;

import java.io.*;
import java.nio.file.Files;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private List<E> list = new ArrayList<>();;
    private final File file;

    public SavedList(File file) throws IOException {
        this.file = file;

        if(!file.exists()){
            Files.createFile(file.toPath());
        }
        else
        {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                list = (List<E>) objectInputStream.readObject();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }
    }

    private void writeFile() {
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStream.writeObject(list);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E setted = list.set(index, element);
        writeFile();
        return setted;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        writeFile();
    }

    @Override
    public E remove(int index) {
        E removed = list.remove(index);
        writeFile();
        return removed;
    }
}
