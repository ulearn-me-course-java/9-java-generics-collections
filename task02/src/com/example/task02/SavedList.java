package com.example.task02;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private List<E> objects;
    private final Path file;

    public SavedList(File file) throws IOException {
        this.file = file.toPath();
        if (!file.exists()) {
            Files.createFile(this.file);
            objects = new ArrayList<>();
        } else {
            try (ObjectInputStream in = new ObjectInputStream(
                    Files.newInputStream(this.file))) {
                objects = ((ArrayList<E>) in.readObject());
            } catch (Exception ex) {
                System.out.println(ex.getMessage());
            }
        }
    }

    @Override
    public E get(int index) {
        return objects.get(index);
    }

    @Override
    public E set(int index, E element) {
        try (ObjectOutputStream out = new ObjectOutputStream(
                Files.newOutputStream(file)))  {
            E result = objects.set(index, element);
            out.writeObject(objects);
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public int size() {
        return objects.size();
    }

    @Override
    public void add(int index, E element) {
        try (ObjectOutputStream out = new ObjectOutputStream(
                Files.newOutputStream(file))) {
            objects.add(index, element);
            out.writeObject(objects);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public E remove(int index) {
        try(ObjectOutputStream out = new ObjectOutputStream(
                Files.newOutputStream(file))) {
            E result = objects.remove(index);
            out.writeObject(objects);
            return result;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
