package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.LinkedList;
import java.util.List;

public class SavedList<E> extends AbstractList<E> {
    private static final List<File> fileStorage = new LinkedList<>();
    private final File currentFile;
    private final LinkedList<E> listStorage = new LinkedList<>();

    public SavedList(File file) {
        int indexOgFile = fileStorage.indexOf(file);
        if (indexOgFile != -1){
            currentFile = fileStorage.get(indexOgFile);
            try {
                if(file.exists()) ///remove from list this file
                    listStorage.addAll(readFile());
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        else {
            currentFile = file;
            fileStorage.add(currentFile);
        }
    }

    @Override
    public E get(int index) {
        return listStorage.get(index);
    }

    @Override
    public E set(int index, E element) {
        listStorage.set(index, element);
        writeInfo(false);
        return element;
    }

    @Override
    public int size() {
        return listStorage.size();
    }

    @Override
    public void add(int index, E element) {
        listStorage.add(index, element);
         writeInfo(false);
    }

    @Override
    public boolean add(E element) {
        listStorage.add(element);
        writeInfo(true);
        return true;
    }

    private void writeInfo(boolean isAppend){
        try(ObjectOutputStream objectOutputStream = new ObjectOutputStream(new FileOutputStream(currentFile, isAppend))){
            if(!isAppend) rewriteFile(objectOutputStream);
            else objectOutputStream.writeObject(listStorage.getLast());
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void rewriteFile(ObjectOutputStream objectOutputStream){
        listStorage.forEach(i -> {
            try {
                objectOutputStream.writeObject(i);
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public E remove(int index) {
        E value = listStorage.remove(index);

        writeInfo(false);
        return value;
    }


    private List<E> readFile() throws IOException, ClassNotFoundException {
        List<E> resultList = new LinkedList<>();
        try(ObjectInputStream objectInputStream = new ObjectInputStream(new FileInputStream(currentFile))) {
           while (true){
               try{
                   resultList.add((E) objectInputStream.readObject());
               }catch (EOFException e){
                   return resultList;
               }
           }
        }
    }
}
