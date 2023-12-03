package com.example.task02;

import java.io.File;
import java.io.Serializable;
import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;

public class SavedList<E extends Serializable> extends AbstractList<E>
{
    private File DiskList;
    private ArrayList<E> ArrList = new ArrayList<E>();
    public SavedList(File file)
    {
       DiskList = file;
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file)))
        {
            ArrList = (ArrayList<E>) ois.readObject();
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    @Override
    public E get(int index)
    {
        E sv = ArrList.get(index);
        return sv;
    }

    @Override
    public E set(int index, E element)
    {
        E sv = ArrList.set(index, element);
        ListWrite();
        return sv;
    }

    @Override
    public int size()
    {
        int sv = ArrList.size();
        return sv;
    }

    @Override
    public void add(int index, E element)
    {
        ArrList.add(index,element);
        ListWrite();
    }

    @Override
    public E remove(int index)
    {
        E sv = ArrList.remove(index);
        ListWrite();
        return sv;
    }

    public void ListWrite()
    {
        if(DiskList.exists())
        {
            DiskList.delete();
        }
        try (ObjectOutputStream ois = new ObjectOutputStream(new FileOutputStream(DiskList))) {
            ois.writeObject(ArrList);
        } catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
}
