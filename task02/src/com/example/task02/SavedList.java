package com.example.task02;

import java.io.File;
import java.io.Serializable;
import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private File file;
    private ArrayList<E> list;
    public SavedList(File file) {
        this.file = file;
        if (file.exists())
        {
            try (ObjectInputStream obj = new ObjectInputStream(new FileInputStream(file)))
            {
                list = (ArrayList<E>) obj.readObject();
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        else
        {
            list = new ArrayList<E>();
        }
    }

    @Override
    public E get(int index) {
        return this.list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E elem = this.list.set(index, element);
        overwriteFile();
        return elem;
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public void add(int index, E element) {
        this.list.add(index, element);
        overwriteFile();
    }

    @Override
    public E remove(int index) {
        E elem = this.list.remove(index);
        overwriteFile();
        return elem;
    }
    private void overwriteFile(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(this.file))) {
            oos.writeObject(this.list);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
