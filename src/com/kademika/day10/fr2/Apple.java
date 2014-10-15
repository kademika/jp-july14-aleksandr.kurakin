package com.kademika.day10.fr2;

/**
 * Created by kurakinaleksandr on 28.06.14.
 */
public class Apple extends Fruit {
    public String sort;

    public Apple (){
        this.setName("Apple");
    }

    public String getSort() {
        return sort;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }
}
