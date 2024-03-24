package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private final File file;
    private List<E> list;

    public SavedList(File file) {
        this.file = file;

        if (file.exists()) {
            try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
                list = (ArrayList<E>) ois.readObject();
            } catch (ClassNotFoundException e) {
                System.out.println("Class not found!: ");
            } catch (FileNotFoundException e) {
                System.out.println("File not found!: ");
            } catch (IOException e) {
                System.out.println("IOException: " + e.getMessage());
            }
        } else {
            list = new ArrayList<>();
        }

    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E value = list.set(index, element);
        saveChanges();

        return value;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        saveChanges();
    }

    @Override
    public E remove(int index) {
        E value = list.get(index);
        list.remove(index);
        saveChanges();

        return value;
    }

    private void saveChanges() {
        try (ObjectOutputStream ous = new ObjectOutputStream(new FileOutputStream(file))) {
            ous.writeObject(list);
        } catch (IOException e) {
            System.out.println("IOException: " + e.getMessage());
        }
    }
}
