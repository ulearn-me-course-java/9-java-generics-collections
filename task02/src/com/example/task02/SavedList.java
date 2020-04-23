package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;

public class SavedList<E extends Serializable> extends AbstractList<E> implements Serializable {

    ArrayList<E> list;
    File file;

    public SavedList(File file) {

        if (!file.exists()) {
            list = new ArrayList<>();
            try {
                file.createNewFile();
            } catch (IOException ex) {
                System.out.print(ex.getMessage());
            }
        } else {
            try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
                list = ((SavedList<E>) input.readObject()).list;
            } catch (Exception ex) {
                System.out.print(ex.getMessage());
            }
        }

        this.file = file;
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {

        //научите придумывать нормальные имена переменным пожалуйста
        E elemen = list.set(index, element);
        update();
        return elemen;
        //return list.set(index,element);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        update();
    }

    @Override
    public E remove(int index) {
        E elemen = list.remove(index);
        update();
        return elemen;
    }

    private void update() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
            out.writeObject(this);
        } catch (Exception ex) {

        }

    }
}
