package com.kademika.shop.entitys;

import java.io.Serializable;

public class Customer implements Serializable{
	private String name;

    public void setName(String name) {
        this.name = name;
    }


	public String getName() {
		return name;
	}

}
