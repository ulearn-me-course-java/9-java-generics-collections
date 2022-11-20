package com.example.task02;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractList;
import java.util.LinkedList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private List<E> data;
    File file;
    public SavedList(File file) {
        this.file = file;
        data = readFile(file.toPath());
    }

    @Override
    public E get(int index) {
        return data.get(index);
    }

    @Override
    public E set(int index, E element) {
        E elem = data.set(index, element);
        writeFile(file.toPath());
        return elem;
    }

    @Override
    public int size() {
        return data.size();
    }

    @Override
    public void add(int index, E element) {
        data.add(index, element);
        writeFile(file.toPath());
    }

    @Override
    public E remove(int index) {
        E elem = data.remove(index);
        writeFile(file.toPath());
        return elem;
    }

    private List<E> readFile(Path path){
        List<E> list = new LinkedList<>();
        Object obj;
        try(ObjectInputStream inputStream = new ObjectInputStream(Files.newInputStream(path)) ) {
            while ((obj = inputStream.readObject()) != null){
                list.add((E)obj);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return list;
    }
    private void writeFile(Path path){
        try(ObjectOutputStream outputStream = new ObjectOutputStream(Files.newOutputStream(path))) {
            for(E elem : data){
                outputStream.writeObject(elem);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
