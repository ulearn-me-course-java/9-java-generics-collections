package com.example.task02;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private List<E> list = new ArrayList<E>();
    private final File file;

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
        E t = this.list.get(index);
        this.list.set(index, element);
        rewriteFile();
        return t;
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
        E t = this.list.remove(index);
        rewriteFile();
        return t;
    }

    private void rewriteFile() {
        try (ObjectOutputStream oos = new ObjectOutputStream(Files.newOutputStream(Paths.get(this.file.getPath()))))
        {
            oos.writeObject(list);
        }
        catch (Exception e) {
            throw new RuntimeException("Cannot save file");
        }
    }

    private void loadFile()  {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(this.file))){
            this.list = (ArrayList) ois.readObject();
        }
        catch (Exception e){
            throw new RuntimeException("Cannot load file");
        }
    }
}