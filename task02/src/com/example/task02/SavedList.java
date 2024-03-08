package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private File file;
    private List<E> elem;

    public SavedList(File file) {
        if (!file.exists()){
            elem = new ArrayList<E>();
        }
        else {
            uploadList(file);
        }
        this.file = file;
    }

    private void uploadList(File file){
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file)))
        {
            elem = (List<E>) objectInputStream.readObject();
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    private void writeList(){
        try(ObjectOutputStream objectOutputStreamStream = new ObjectOutputStream(new FileOutputStream(file))) {
            objectOutputStreamStream.writeObject(elem);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public E get(int index) {
        return elem.get(index);
    }

    @Override
    public E set(int index, E element) {
        E result = elem.set(index,element);
        writeList();
        return result;
    }

    @Override
    public int size() {
        return elem.size();
    }

    @Override
    public void add(int index, E element) {
        elem.add(index,element);
        writeList();
    }

    @Override
    public E remove(int index) {
        E resOfRemove = elem.remove(index);
        writeList();
        return resOfRemove;
    }
}
