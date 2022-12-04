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
        if(file.exists()) {
            try(ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
                list = (ArrayList<E>) is.readObject();
            } catch (IOException | ClassNotFoundException ignored) { }
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
    public E set(int index, E element) {
        E old = list.set(index, element);
        rewriteFile();
        return old;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        rewriteFile();
    }

    @Override
    public E remove(int index) {
        E removed = list.remove(index);
        rewriteFile();
        return removed;
    }

    private void rewriteFile() {
        try(ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
            os.writeObject(list);
        } catch (IOException ignored) { }
    }
}
