package com.example.task02;

import java.io.*;
import java.util.*;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private final File file;
    private List<E> list;

    public SavedList(File file) {
        this.file = file;
        if(file.exists()){
            try(ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))){
                list = (ArrayList<E>) ois.readObject();
            } catch (Exception exception){}
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
        E elem = list.set(index, element);
        saveList();
        return elem;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        saveList();
    }

    @Override
    public E remove(int index) {
        E elem = list.remove(index);
        saveList();
        return elem;
    }

    private void saveList(){
        try(ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(file))){
            ous.writeObject(list);
        } catch (Exception exception) {}
    }
}
