package com.example.task02;

import java.io.*;
import java.nio.file.Path;
import java.util.AbstractList;
import java.util.ArrayList;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private File HddList;
    private ArrayList<E> RamList = new ArrayList<E>();
    public SavedList(File file) {
        HddList = file;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            RamList = (ArrayList<E>) ois.readObject();
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public E get(int index) {
        E ret = RamList.get(index);
        return ret;
    }

    @Override
    public E set(int index, E element) {
        E ret = RamList.set(index, element);
        ListChanged();
        return ret;
    }

    @Override
    public int size() {
        int ret = RamList.size();
        return ret;
    }

    @Override
    public void add(int index, E element) {
        RamList.add(index,element);
        ListChanged();
    }

    @Override
    public E remove(int index) {
        E ret = RamList.remove(index);
        ListChanged();
        return ret;
    }

    public void ListChanged()
    {
        if(HddList.exists())
        {
            HddList.delete();
        }
        try (ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(HddList))) {
            ois.writeObject(RamList);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
