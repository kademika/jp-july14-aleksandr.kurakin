package com.kademika.day10.fr2;

/**
 * Created by kurakinaleksandr on 28.06.14.
 */
public class Banana extends Fruit {

    public String color;

    public Banana(){
        this.setName("Banana");
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
