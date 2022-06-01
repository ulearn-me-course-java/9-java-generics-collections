package com.example.task02;

import java.io.File;
import java.io.Serializable;
import java.util.AbstractList;
import java.util.*;
import java.io.*;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private final File file;
    private List<E> list;

    public SavedList(File file)
    {
        this.file = file;
        if(file.exists()){
            try(ObjectInputStream objsFromFile = new ObjectInputStream(new FileInputStream(file)))
            {
                list = (ArrayList<E>) objsFromFile.readObject();
            }
            catch (Exception ex) {System.out.println(ex.getMessage());}
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
    public E set(int index, E element)
    {
        E newItem = list.set(index,element);
        saveList();
        return newItem;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element)
    {
        list.add(index, element);
    }

    @Override
    public E remove(int index)
    {
        E deleted = list.remove(index);
        saveList();
        return deleted;
    }

    private void saveList()
    {
        try(ObjectOutputStream objsToFile = new ObjectOutputStream(new FileOutputStream(file))){
            objsToFile.writeObject(list);
        } catch (Exception ex) {System.out.println(ex.getMessage());}
    }

}
