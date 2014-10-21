package com.kademika.day11.zipArchiver;

import java.io.File;
import java.io.IOException;

/**
 * Created by kurakinaleksandr on 27.08.14.
 */

public class ZipArchiver {
    public static void main(String filePath, String command) {
        ZipArchivUtils engine = new ZipArchivUtils();
        if (command == "zip") {
            engine.zip(new File(filePath));
        } else if (command == "unzip") {
            try {
                engine.unZip(new File(filePath));
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("You entered invalid command, please try again. ");
            System.out.println("Help: zip - to make zip archive or unzip - to unzip archive");
        }
    }
}
