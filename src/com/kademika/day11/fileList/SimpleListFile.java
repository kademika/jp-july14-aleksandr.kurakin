package com.kademika.day11.fileList;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by kurakinaleksandr on 03.09.14.
 */
public class SimpleListFile extends ArrayList {
    public File storage = new File("/Users/kurakinaleksandr/dropbox/jc-a_kurakin/src/com/kademika/day11/fileList/storage.txt");
    SimpleListFileUtils slfUtils = new SimpleListFileUtils();
    List<String> storageList = slfUtils.readFromFile(storage);


    //main methods
    @Override
    public boolean add(Object o) {
        boolean result = storageList.add(o.toString());
        slfUtils.writeToFile(storageList, storage);
        return result;
    }

    @Override
    public Object get(int index) {
        String result = null;
        if (index > storageList.size()) {
            System.out.println("Index is out of bounds");
        } else {
            result = storageList.get(index);
        }
        return result;
    }

    @Override
    public String remove(int index) {
        String result = storageList.remove(index);
        slfUtils.writeToFile(storageList, storage);
        return result;
    }

    @Override
    public boolean contains(Object o) {
        boolean result = false;
        if (storageList.contains(o.toString())) {
            result = true;
        }
        return result;
    }

    @Override
    public int size() {
        return storageList.size();
    }

    //Iterator
    @Override
    public Iterator iterator() {
        Iterator iterator = new Iterator() {
            private int currentIndex = 0;

            @Override
            public boolean hasNext() {
                return currentIndex < storageList.size() && storageList.get(currentIndex) != null;
            }

            @Override
            public Object next() {
                return storageList.get(currentIndex++);
            }

            @Override
            public void remove() {
                storageList.remove(currentIndex);
                slfUtils.writeToFile(storageList, storage);
            }
        };
        return iterator;
    }
}

//service class
class SimpleListFileUtils {

    // reading from storageFile
    public List<String> readFromFile(File storageFile) {
        List<String> storageList = new ArrayList();
        try {
            BufferedReader in = new BufferedReader(new FileReader(storageFile));
            String s;
            while ((s = in.readLine()) != null) {
                if (!s.isEmpty()) {
                    storageList.add(s);
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return storageList;
    }

    //write to storageFile
    public void writeToFile(List<String> storageList, File storageFile) {
        int capacity = storageList.size();
        try {
            PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(storageFile)));
            for (int i = 0; i < capacity; i++) {
                out.println(storageList.get(i));
            }
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}


