package com.kademika.day10.fr2;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by kurakinaleksandr on 28.06.14.
 */
public class FruitBox<T extends Fruit> {

    private List<T> items;

    public FruitBox(){

        items = new ArrayList<>();
    }

    public void add(T element) {
             // check ??
            items.add(element);
        }

    public void sortByPrice (){
        T tmp1;
        int activeLength = items.size()-1;

        for (int i = 0; i<items.size()-1; i++){
            int n = 0;
            T tmp2 = null;
            for (int k=0; k<=activeLength; k++){
                if (items.get(k) != null){
                    tmp1 = items.get(k);
                    if (tmp2 != null && tmp2.getPrice()>tmp1.getPrice()){
                        items.set(n, tmp1);
                        items.set(k, tmp2);
                    }
                    tmp2 = items.get(k);
                    n = k;
                }
            }  activeLength --;
        }

    }

    public List<T> getFruits(){
        return new ArrayList<T>(items);
    }

    public <T> void copyContaner(List<? extends Fruit> src, List<? super Fruit> dest){
        for (Fruit fruit:src){
            dest.add(fruit);
        }
    }

}
