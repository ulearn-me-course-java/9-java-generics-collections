package com.example.task02;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.NoSuchFileException;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private List<E> list;
    private File file;
    public SavedList(File file) {
        this.file = file;
        try(ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(file.toPath()))) {
            list = (List<E>) inputStream.readObject();
        }
        catch (NoSuchFileException ex) {
            list = new ArrayList<>();
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
    public void writeToFile() {
        try(ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(file.toPath()))) {
            outputStream.writeObject(list);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E newElem = list.set(index, element);
        writeToFile();
        return newElem;
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
        E deletedElem = list.remove(index);
        writeToFile();
        return deletedElem;
    }
}
