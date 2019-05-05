package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private static final List<File> files = new ArrayList<>();
    private List<E> list = new LinkedList<>();

    private final File file;

    public SavedList(File file) {
        if (files.contains(file)) {
            this.file = files.get(files.indexOf(file));
            if (file.exists()) {
                try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                    while (true) {
                        try {
                            list.add((E) ois.readObject());
                        } catch (EOFException e) {
                            break;
                        }
                    }
                } catch (ClassNotFoundException | IOException ex) {
                    ex.printStackTrace();
                }
            } else files.remove(file);

        } else {
            this.file = file;
            files.add(file);
        }
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E elem = list.set(index, element);
        reWriteFile();
        return elem;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        reWriteFile();
    }

    @Override
    public E remove(int index) {
        E elem = list.remove(index);
        reWriteFile();
        return elem;
    }

    private void reWriteFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file))) {
            list.forEach(obj -> {
                try {
                    oos.writeObject(obj);
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
            });
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
}
