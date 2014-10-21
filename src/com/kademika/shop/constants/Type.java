package com.kademika.shop.constants;

public enum Type {
	FARM(0), WILD(1), DECORATE(2);
	private int id;

	private Type(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
