package com.kademika.tanksGame;

import java.io.File;

public class Launcher {

	public static void main(String[] args) throws Exception {

        //clear the history file
		ActionField af = new ActionField();
        af.clearHistory(new File("history.txt"));

        //run the game
		af.runTheGameWithWriteToFile();
//        af.replay();
	}

}
