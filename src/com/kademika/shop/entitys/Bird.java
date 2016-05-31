package com.kademika.shop.entitys;

import com.kademika.shop.constants.Type;

public class Bird {
    private String name;
    private Type type;

    private double price;

    public Bird(String name, double price) {
        this.name = name;
        if (name.equals("CHICKEN") || name.equals("DUCK")) {
            this.type = Type.FARM;
        } else if (name.equals("EAGLE") || name.equals("OWL")) {
            this.type = Type.WILD;
        } else if (name.equals("CANARY") || name.equals("PARROT") || name.equals("CROW")) {
            this.type = Type.DECORATE;
        } else System.out.println("ERROR!! Invalid bird name.");


        this.price = price;
    }

    public String getName() {
        return name;
    }

    public Type getType() {
        return type;
    }

    public double getPrice() {
        return price;
    }

}
