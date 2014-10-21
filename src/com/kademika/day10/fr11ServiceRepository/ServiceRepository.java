package com.kademika.day10.fr11ServiceRepository;

import javax.xml.ws.Service;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class ServiceRepository<T>{
    private List<T> items;

    public ServiceRepository(){
        items = new ArrayList<>();
    }

    public void add (T element){
        items.add(element);
    }

    public T get (int index){
        return items.get(index);
    }
    public int getSize(){
        return items.size();
    }
}
