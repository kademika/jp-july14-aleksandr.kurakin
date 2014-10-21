package com.kademika.day10.wildcards;

import com.kademika.day10.fr2.Fruit;

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
        items.add(items.size()+1, (T) element);
        }

    public void sortByPrice (){
        T tmp1;
        T tmp2 = null;
        int n = 0;
        int activeLength = items.size();
        for (int i = 0; i<items.size()-1; i++){
            for (int k=0; k<activeLength; k++){
                if (items.get(k) != null){
                    tmp1 = items.get(k);
                        if (tmp2 != null && tmp2.getPrice()>tmp1.getPrice()){
                          items.add(n, tmp1);
                          items.add(k, tmp2);
                        }
                    tmp2 = tmp1;
                    n = k;
                }
                activeLength --;
            }
        }

    }





    public List<T> getFruits(){
        return new ArrayList<T>(items);
    }


}
