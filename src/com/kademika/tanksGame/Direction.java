package com.kademika.tanksGame;

public enum Direction {
	NONE(0),  UP(1), RIGHT(2), DOWN(3), LEFT(4);
    private int id;

    private Direction (int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
