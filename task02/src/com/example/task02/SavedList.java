package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private final File file;
    private List<E> list = new ArrayList<>();

    public SavedList(File file) {
        this.file = file;
        if (this.file.exists()){
            loadFile();
        }
        else {
            try {
                file.createNewFile();
            }
            catch (Exception e){
                throw new RuntimeException("Cannot create new file");
            }
        }
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E e = this.list.get(index);
        this.list.set(index, element);
        rewriteFile();
        return e;
    }

    @Override
    public int size() {
        return this.list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index,element);
        rewriteFile();
    }

    @Override
    public E remove(int index) {
        E e = this.list.remove(index);
        rewriteFile();
        return e;
    }

    private void rewriteFile() {
        try (ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(file))) {
            ous.writeObject(list);
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
    private void loadFile()  {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.file))){
            list = (ArrayList) ois.readObject();
        }
        catch (Exception e){
            throw new RuntimeException("Cannot load file");
        }
    }
}
