package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private List<E> elements;
    private File file;

    public SavedList(File file) {
        if(!file.exists()){
            elements = new ArrayList<E>();
        }
        else {
            createList(file);
        }
        this.file = file;
    }

    private void createList(File file){
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            elements = (List<E>) objectInputStream.readObject();
        }
        catch (Exception e){

        }
    }

    private void writeList(){
        try(ObjectOutputStream objectOutputStreamStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStreamStream.writeObject(elements);
        }
        catch (Exception e){}
    }

    @Override
    public E get(int index) {
        return elements.get(index);
    }

    @Override
    public E set(int index, E element) {
        E e = elements.set(index, element);
        writeList();
        return e;
    }

    @Override
    public int size() {
        return elements.size();
    }

    @Override
    public void add(int index, E element) {
        elements.add(index, element);
        writeList();
    }

    @Override
    public E remove(int index) {
        E removed = elements.remove(index);
        writeList();
        return removed;
    }
}
