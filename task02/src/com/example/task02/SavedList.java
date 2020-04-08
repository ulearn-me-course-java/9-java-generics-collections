package com.example.task02;
import java.io.File;
import java.io.Serializable;
import java.util.AbstractList;
import java.io.*;
import java.util.*;

    public class SavedList<E extends Serializable> extends AbstractList<E> implements Serializable{
        ArrayList<E> list;
        File file;

        public SavedList(File file) {
            if (!file.exists()) {
                list = new ArrayList<>();
                try {
                    file.createNewFile();
                } catch (IOException ex) {
                    System.out.print(ex.getMessage());
                }
            }
            else {
                try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))){
                    list = ((SavedList<E>) input.readObject()).list;
                } catch (Exception ex) {
                    System.out.print(ex.getMessage());
                }
            }
            this.file = file;
        }

        @Override
        public E get(int index) {
            return list.get(index);
        }

        @Override
        public E set(int index, E element) {
            E elem=list.set(index,element);
            update();
            return elem;
        }

        @Override
        public int size() {
            return list.size();
        }

        @Override
        public void add(int index, E element) {
            list.add(index,element);
            update();
        }

        @Override
        public E remove(int index) {
            E elem=list.remove(index);
            update();
            return elem;
        }

        private void update(){
            try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(file))) {
                out.writeObject(this);
            }catch (Exception ex){
                System.out.print(ex.getMessage());
            }

        }
}