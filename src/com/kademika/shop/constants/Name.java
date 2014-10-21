package com.kademika.shop.constants;

public enum Name {
	CHICKEN(0), DUCK(1), EAGLE(2), OWL(3), PARROT(4), CANARY(5);
	private int id;
	
	private Name(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}