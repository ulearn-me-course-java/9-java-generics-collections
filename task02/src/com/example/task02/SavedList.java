package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private ArrayList<E> _collection = new ArrayList<>();
    private File _file;

    public SavedList(File file) {
        _file = file;

        if(!file.exists())
            _collection = new ArrayList<>();
        else
            ReadObjects(file);

    }

    @Override
    public E get(int index) {
        return _collection.get(index);
    }

    @Override
    public E set(int index, E element) {
        E changedElement = _collection.set(index, element);
        WriteObjects();
        return changedElement;
    }

    @Override
    public int size() {
        return _collection.size();
    }

    @Override
    public void add(int index, E element) {
        _collection.add(index, element);
        WriteObjects();
    }

    @Override
    public E remove(int index) {
        E element = _collection.remove(index);
        WriteObjects();
        return element;
    }

    private void WriteObjects(){
        try(ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(_file))){
                stream.writeObject(_collection);
        }catch(Exception ex){
            ex.printStackTrace();
        }
    }

    private void ReadObjects(File file){
        try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
            _collection = (ArrayList<E>) objectInputStream.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
