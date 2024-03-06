package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private final File file;
    private List<E> list;

    public SavedList(File file) {
        if (!file.exists()){
            list = new ArrayList<>();
        }
        else {
            try (ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(file))) {
                list = (List<E>) objectInputStream.readObject();
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        }

        this.file = file;
    }

    private void writeFile(){
        try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(file))){
            objectOutputStream.writeObject(list);
        }
        catch (Exception ex){
            ex.printStackTrace();
        }
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E setList = list.set(index, element);
        writeFile();
        return setList ;
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
        E removeList = list.remove(index);
        writeFile();
        return removeList;
    }
}