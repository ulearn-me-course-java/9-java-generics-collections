package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private List<E> list;
    private File file;
    public SavedList(File file) {
        this.file = file;
        if(!file.exists()){
            list = new ArrayList<>();
        }
        else{
            try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                list = (ArrayList<E>) objectInputStream.readObject();
            }  catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E value =  list.set(index, element);
        writeFile();
        return value;
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
        E value = list.remove(index);
        writeFile();
        return value;
    }

    private void writeFile(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            oos.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
