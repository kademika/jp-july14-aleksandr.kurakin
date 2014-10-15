package com.kademika.day10.fr2;

/**
 * Created by kurakinaleksandr on 25.06.14.
 */
public class Fruit {
    private long id;
    private String name;
    private double price;


    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getId() {

        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
    @Override
    public String toString() {
        return name + ": " + price;
    }
}

