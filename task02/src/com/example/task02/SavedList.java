package com.example.task02;

import java.io.*;
import java.nio.file.Path;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E>{

    List<E> list = new ArrayList();
    File f;
    public SavedList(File file){
        f = file;
       try(ObjectInputStream objects = new ObjectInputStream(new FileInputStream(file))) {
           list=(List<E>)objects.readObject();
       }
       catch (Exception e){
           System.out.println(e.getMessage());
       }

    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        remove(index);
        add(index, element);
        return element;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        fillFile();
    }

    @Override
    public E remove(int index) {
        E el = list.remove(index);
        fillFile();
        return el;
    }

    private  void fillFile(){
        if (f.exists()) {
            f.delete();
        }
        try(ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(f))){
            oos.writeObject(list);
        }
        catch (Exception e ){
            System.out.println(e.getMessage());
        }
    }
}
