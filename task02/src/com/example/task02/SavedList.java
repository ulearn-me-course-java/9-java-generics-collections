package com.example.task02;

import java.io.*;
import java.util.AbstractList;
import java.util.ArrayList;
import java.util.List;

public class SavedList<E extends Serializable> extends AbstractList<E>
{
    private final File file;
    private List<E> list;

    public SavedList(File file) throws IOException
    {
        this.file = file;
        getListFromFile();
    }

    @Override
    public E get(int index)
    {
        return list.get(index);
    }

    @Override
    public E set(int index, E element)
    {
        E result = list.set(index, element);
        saveListToFile();
        return result;
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
        saveListToFile();
    }

    @Override
    public E remove(int index)
    {
        E result = list.remove(index);
        saveListToFile();
        return result;
    }

    private void saveListToFile()
    {
        try (ObjectOutputStream os = new ObjectOutputStream(new FileOutputStream(file)))
        {
            os.writeObject(list);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
    }

    private void getListFromFile() throws IOException
    {
        list = new ArrayList<>();
        if (file.exists())
            try (ObjectInputStream os = new ObjectInputStream(new FileInputStream(file)))
            {
                list = (List<E>) os.readObject();
            }
            catch (Exception e)
            {
                System.out.println(e.getMessage());
            }
        else
            file.createNewFile();
    }
}
