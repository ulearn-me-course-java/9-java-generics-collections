package com.example.task02;

import java.io.*;
import java.util.*;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private static final long serialVersionUID = 42L;
    public List<E> list;
    private String pathToFile;

    public SavedList(File file) throws IOException {

        pathToFile = file.getAbsolutePath();

        if (!file.exists()) {
            list = new ArrayList<>();
            new PrintWriter(pathToFile, "UTF-8").close();
            updateFile();
        } else {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(pathToFile))) {
                list = (ArrayList<E>) ois.readObject();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    private void updateFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(pathToFile))) {
            oos.writeObject(list);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
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
        E setted = list.set(index, element);
        updateFile();
        return setted;
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
        E removedEl = list.remove(index);
        updateFile();
        return removedEl;
    }
}
