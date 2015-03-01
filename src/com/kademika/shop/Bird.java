package com.kademika.shop;

import com.kademika.shop.constants.Name;
import com.kademika.shop.constants.Type;

public class Bird {
	private Name name;
	private Type type;
	
	private double price;
	
	public Bird (Name name, double price){
		this.name = name;
		if (name==Name.CHICKEN||name==Name.DUCK){
			this.type = Type.FARM;
		} else if (name==Name.EAGLE||name==Name.OWL){
			this.type = Type.WILD;
		} else if (name==Name.CANARY||name==Name.PARROT){
			this.type = Type.DECORATE;
		} else System.out.println("ERROR!! Invalid bird name.");

		
		this.price = price;
	}

	public Name getName(){
        return name;
    }

	public Type getType() {
		return type;
	}
	
	public double getPrice() {
		return price;
	}
	
}
