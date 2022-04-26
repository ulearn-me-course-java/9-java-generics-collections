package com.example.task02;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

/*
Реализуйте класс SavedList, представляющий собой список элементов с произвольным доступом,
копия которого хранится в виде файла на жестком диске.
-SavedList должен обновлять содержимое файла при каждом изменении списка элементов.
-При создании нового экземпляра SavedList с указанием существующего файла,
он должен загрузить список элементов из этого файла.
-SavedList должен корректно обрабатывать ситуацию отсутствия файла
(отсутствие файла означает отсутствие элементов в списке).
-Для хранения списка в оперативной памяти можно использовать коллекции из java.util.
-Для сохранения и загрузки элементов можно использовать ObjectOutputStream и ObjectInputStream.
 */

public class SavedList<E extends Serializable> extends AbstractList<E> {

    private List<E> list = new ArrayList<E>();
    private File file;

    public SavedList(File file) {
        this.file = file;

        if (this.file.exists()){
            load();
        }
        else {
            try {
                file.createNewFile();
            }
            catch (Exception e){
                throw new RuntimeException("File can't be created");
            }
        }
    }

    private void load() {
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
            list = (ArrayList<E>) ois.readObject();
            ois.close();
        } catch (Exception e){
            throw new RuntimeException("Cannot load file");
        }
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E last = list.get(index);
        list.set(index, element);
        rewriteFile();
        return last;
    }

    private void rewriteFile() {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
            oos.writeObject(list);
            oos.close();
        } catch (Exception e) {
            throw new RuntimeException("Can not save file");
        }
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element) {
        list.add(index,element);
        rewriteFile();
    }

    @Override
    public E remove(int index) {
        E element = list.remove(index);
        rewriteFile();
        return element;
    }
}
