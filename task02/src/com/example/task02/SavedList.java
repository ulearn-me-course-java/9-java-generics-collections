package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private ArrayList<E> arrList;
    private  File file;

    public SavedList(File file) {
        this.file = file;
        if(file.exists()){
            toDeserialize();
        }else{
            arrList = new ArrayList<E>();
        }
    }

    @Override
    public E get(int index) {
        return arrList.get(index);
    }

    @Override
    public E set(int index, E element) {
        E value = arrList.set(index, element);
        toSerialize();
        return  value;
    }

    @Override
    public int size() {
        return arrList.size();
    }

    @Override
    public void add(int index, E element) {
        arrList.add(index, element);
        toSerialize();
    }

    @Override
    public E remove(int index) {
        E value = arrList.remove(index);
        toSerialize();
        return value;
    }

    private void toDeserialize(){
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(this.file));
            arrList = (ArrayList<E>) objectInputStream.readObject();
            objectInputStream.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void toSerialize(){
        try {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file));
            objectOutputStream.writeObject(arrList);
            objectOutputStream.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
