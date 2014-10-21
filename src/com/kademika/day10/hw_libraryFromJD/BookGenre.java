package com.kademika.day10.hw_libraryFromJD;

public enum BookGenre {
	FANTASY(0), FOOD(1), COMPUTERS(2), FICTION(3), HISTORY(4), DETECTIVE(5);

	private int id;

	private BookGenre(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}
}
