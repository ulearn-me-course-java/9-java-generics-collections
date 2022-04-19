package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private List<E> fastMemoryList = new ArrayList<>();
    private File file;
    public SavedList(File file) {
        this.file = file;
        if(file.exists())
        {
            try{
                ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file));
                fastMemoryList = (List<E>)stream.readObject();
            }
            catch (Exception e){};
        }
    }

    private void updateSaveFile(){
        try{
            ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file));
            out.writeObject(fastMemoryList);
        }catch (Exception e){

        }
    }

    @Override
    public E get(int index) {
        return fastMemoryList.get(index);
    }

    @Override
    public E set(int index, E element) {
        E elem = fastMemoryList.set(index, element);
        updateSaveFile();
        return elem;
    }

    @Override
    public int size() {
        return fastMemoryList.size();
    }

    @Override
    public void add(int index, E element) {
        fastMemoryList.add(index, element);
        updateSaveFile();
    }

    @Override
    public E remove(int index) {
        E elem = fastMemoryList.remove(index);
        updateSaveFile();
        return elem;
    }
}
