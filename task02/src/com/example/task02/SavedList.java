package com.example.task02;

import java.io.*;
import java.nio.file.Files;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private List<E> items = new ArrayList<>();
    private File file;

    public SavedList(File file) throws IOException {
        this.file = file;

        if (file.exists()) {
            readItems(file);
        } else {
            Files.createFile(file.toPath());
        }
    }

    @Override
    public E get(int index) {
        return items.get(index);
    }

    @Override
    public E set(int index, E element) {
        E item = items.set(index, element);
        updateFile();

        return item;
    }

    @Override
    public int size() {
        return items.size();
    }

    @Override
    public void add(int index, E element) {
        items.add(index, element);
        updateFile();
    }

    @Override
    public E remove(int index) {
        E item = items.remove(index);
        updateFile();

        return item;
    }

    private void updateFile() {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file))) {
            for (E item : items) {
                os.writeObject(item);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void readItems(File file) {
        Object cur;
        try (ObjectInputStream is = new ObjectInputStream(new FileInputStream(file))) {
            while ((cur = is.readObject()) != null)
                items.add((E) cur);
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
