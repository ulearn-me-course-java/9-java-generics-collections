package com.example.task02;

import java.io.*;
import java.nio.file.Files;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E> {
    private final List<E> _itemList;
    private final File _file;

    public SavedList(File file) throws IOException {
        _itemList = new ArrayList<>();
        _file = file;

        if (!_file.exists()) {
            Files.createFile(_file.toPath());
        } else {
            fillItemList();
        }
    }

    @Override
    public E get(int index) {
        return _itemList.get(index);
    }

    @Override
    public E set(int index, E element) {
        E item = _itemList.set(index, element);
        rewriteData();

        return item;
    }

    @Override
    public int size() {
        return _itemList.size();
    }

    @Override
    public void add(int index, E element) {
        _itemList.add(index, element);
        rewriteData();
    }

    @Override
    public E remove(int index) {
        E item = _itemList.remove(index);
        rewriteData();

        return item;
    }

    private void fillItemList() throws IOException {
        try (FileInputStream inputStream = new FileInputStream(_file)) {
            if (inputStream.available() != 0) {
                try (ObjectInputStream objectInputStream = new ObjectInputStream(inputStream)) {
                    fillItemListFromFile(objectInputStream);
                }
            }
        }
    }

    private void fillItemListFromFile(ObjectInputStream objectInputStream) {
        E obj = getObject(objectInputStream);
        while (obj != null) {
            _itemList.add(obj);
            obj = getObject(objectInputStream);
        }
    }

    private E getObject(ObjectInputStream objectInputStream) {
        try {
            Object obj = objectInputStream.readObject();
            return (E) obj;
        } catch (Exception e) {
            return null;
        }
    }

    private void rewriteData() {
        try (FileOutputStream fileOutputStream = new FileOutputStream(_file)) {
            try (ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
                objectOutputStream.reset();
                for (E item : _itemList) {
                    objectOutputStream.writeObject(item);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
