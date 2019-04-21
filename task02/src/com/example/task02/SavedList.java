package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E>  {

    private List<E> list;
    private File outFile;

    public SavedList(File file) throws ClassNotFoundException{
        if(file.exists()) {
            try {
                ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
                ArrayList<E> tempList = (ArrayList<E>) ois.readObject();
                this.list = tempList;
                this.outFile = file;
            } catch (IOException e) {
                e.getStackTrace();
            }
        }
        else {
            this.outFile = file;
            this.list = new ArrayList<>();
        }
    }

    @Override
    public E get(int index) {
        return list.get(index);
    }

    @Override
    public E set(int index, E element) {
        E el = list.get(index);
        list.set(index, element);
        save();
        return el;
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(int index, E element){
        list.add(index,element);
        save();
    }

    @Override
    public E remove(int index) {
        E el = list.get(index);
        list.remove(index);
        save();
        return el;
    }

    private void save(){
        try {
            if(!outFile.exists()){
                outFile.createNewFile();
            }
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outFile));
            oos.writeObject(this.list);
            oos.flush();
            oos.close();
        }
        catch (IOException e){
            e.getStackTrace();
        }
    }
}
