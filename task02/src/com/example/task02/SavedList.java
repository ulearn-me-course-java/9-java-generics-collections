package com.example.task02;

import org.assertj.core.internal.bytebuddy.matcher.LatentMatcher;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E>{
    private List<E> values;
    private Path path;
    public SavedList(File file){
        path = file.toPath();
        if(file.exists()){
            ReadFromFile();
        }
        else{
            values = new ArrayList<>();
        }
    }

    @Override
    public E get(int index) {
        return values.get(index);
    }

    @Override
    public E set(int index, E element) {
        E temp = values.set(index, element);
        WriteToFile();
        return temp;
    }

    @Override
    public int size() {
        return values.size();
    }

    @Override
    public void add(int index, E element) {
        values.add(index, element);
        WriteToFile();
    }

    @Override
    public E remove(int index) {
        E temp = values.remove(index);
        WriteToFile();
        return temp;
    }

    private void ReadFromFile(){
        try (ObjectInputStream reader = new ObjectInputStream(
                Files.newInputStream(path))) {
            values = ((ArrayList<E>) reader.readObject());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    private void WriteToFile(){
        try(ObjectOutputStream writer = new ObjectOutputStream(
                Files.newOutputStream(path))){
            writer.writeObject(values);
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
