package com.example.task02;

import java.io.*;
import java.nio.file.Files;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private List<E> list;
    private File file;

    public SavedList(File file) {
        this.file = file;
        list = new ArrayList<>();
        if (file.exists()) {
            try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
                list = (ArrayList<E>) in.readObject();
            } catch (Exception e) {

            }
        }
    }



    private void WriteInFile(File file){
        try(ObjectOutputStream out = new ObjectOutputStream(Files.newOutputStream(file.toPath()));){
            out.writeObject(list);

        } catch (IOException e ) {
            e.printStackTrace();
        }
    }
    private void DeleteAllInFile(File file) {
        PrintWriter writer = null;
        try {
            writer = new PrintWriter(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        writer.print("");
        writer.close();
    }

    private void rewriteFile(File file){
        DeleteAllInFile(file);
        WriteInFile(file);
    }


    @Override
    public E get(int index) {
        return list.get(index); // как это влияет на сохранение элементов в списке?
    }

    @Override
    public E set(int index, E element) {
        E smt = list.set(index, element);
        rewriteFile(file);
        return smt;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index, element);
        rewriteFile(file);
    }

    @Override
    public E remove(int index) {
        E val = list.remove(index);
        rewriteFile(file);
        return val;
    }
}
