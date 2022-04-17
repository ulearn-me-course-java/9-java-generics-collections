package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.Objects;

public class SavedList<E extends Serializable> extends AbstractList<E>
{
    private ArrayList<E> list;
    private File file;

    public SavedList(File file)
    {
        this.file = file;
        if (file.exists())
        {
            try (ObjectInputStream obj = new ObjectInputStream(new FileInputStream(file)))
            {
                list = (ArrayList<E>) obj.readObject();
            }
            catch (Exception ex)
            {
                System.out.println(ex.getMessage());
            }
        }
        else
        {
            list = new ArrayList<E>();
        }
    }

    @Override
    public E get(int index)
    {
        return list.get(index);
    }

    @Override
    public E set(int index, E element)
    {
        E elem = list.set(index, element);
        writlist();
        return elem;
    }

    @Override
    public int size()
    {
        return list.size();
    }

    @Override
    public void add(int index, E element)
    {
        list.add(index, element);
        writlist();
    }

    @Override
    public E remove(int index)
    {
        E elem = list.remove(index);
        writlist();
        return elem;
    }

    private void writlist()
    {
        try (ObjectOutputStream obj = new ObjectOutputStream(new FileOutputStream(file)))
        {
            obj.writeObject(list);
        }
        catch (Exception ex)
        {
            System.out.println(ex.getMessage());
        }
    }
}
