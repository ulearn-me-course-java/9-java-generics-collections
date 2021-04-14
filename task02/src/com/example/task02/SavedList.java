package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    List<E> savedList;
    File file;

    public SavedList(File file) throws IOException {
        this.file = file;
        savedList = new ArrayList<>();
        if (file.exists()){
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))){
                this.savedList = (ArrayList<E>) objectInputStream.readObject();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    @Override
    public E get(int index) {
        return savedList.get(index);
    }

    @Override
    public E set(int index, E element) {
        E oldElement = savedList.get(index);
        savedList.set(index, element);
        save();
        return oldElement;
    }

    @Override
    public int size() {
        return savedList.size();
    }

    @Override
    public void add(int index, E element) {
        savedList.add(index, element);
        save();
    }

    @Override
    public E remove(int index) {
        E deletedElement = savedList.get(index);
        savedList.remove(index);
        save();
        return deletedElement;
    }

    public void save(){
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(savedList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
