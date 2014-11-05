package com.kademika.tanksGame;

import java.io.File;

public class Launcher {

	public static void main(String[] args) throws Exception {

		ActionField af = new ActionField();
        af.clearHistory(new File("history.txt"));
		af.runTheGame();
	}

}
