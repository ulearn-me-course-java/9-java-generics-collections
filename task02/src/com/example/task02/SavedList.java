package com.example.task02;

import javax.sql.rowset.serial.SerialArray;
import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private ArrayList<E> list = new ArrayList<>();
    private File file;

    public SavedList(File file){
        try {
            if (!file.createNewFile()){
                try (ObjectInputStream inputStream = new ObjectInputStream(new FileInputStream(file))){
                    this.file = file;
                    list = (ArrayList<E>)inputStream.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
            else{
                this.file = file;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void rewriteList(){
        try (ObjectOutputStream outputStream = new ObjectOutputStream(new FileOutputStream(file))){
            outputStream.writeObject(list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E temp = list.get(index);
        list.set(index, element);
        rewriteList();
        return temp;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
    }

    @Override
    public E remove(int index) {
        E temp = list.get(index);
        list.remove(index);
        rewriteList();
        return temp;
    }
}
