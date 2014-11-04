package com.kademika.shop;

import com.kademika.shop.constants.Name;
import com.kademika.shop.constants.Type;

public class Bird {
	private Name name;
	private Type type;
	
	private double price;
	
	public Bird (Name name, double price){
		this.name = name;
		if (name.getId()==0||name.getId()==1){
			this.type = Type.FARM;
		} else if (name.getId()==2||name.getId()==3){
			this.type = Type.WILD;
		} else if (name.getId()==4||name.getId()==5){
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
