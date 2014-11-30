package com.kademika.tanksGame;

import java.io.File;

public class Launcher {

	public static void main(String[] args) throws Exception {

		ActionField af = new ActionField();

        MainFrame mf = new MainFrame(af);
        //clear the history file
//        af.clearHistory(new File("history.txt"));

        //run the game
//	    af.runTheGameWithWriteToFile();
//      af.replay();
//      af.runTheGameMT();
	}

}
