package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private String path;
    List<E> list;

    public SavedList(File file) throws IOException {
        path = file.getAbsolutePath();

        if (!file.exists()) {
            list = new ArrayList<>();
            new PrintWriter(path, "UTF-8").close();
            updateFile();
        }
        else {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(path))) {
                list = (ArrayList<E>) ois.readObject();
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
        E settedElement = list.set(index, element);
        updateFile();
        return settedElement;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        updateFile();
    }

    @Override
    public E remove(int index) {
        E removedElement = list.remove(index);
        updateFile();
        return removedElement;
    }

    private void updateFile(){
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(path))) {
            oos.writeObject(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
