package com.kademika.day11.theory;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.OutputStream;
import java.io.PrintStream;

/**
 * Created by kurakinaleksandr on 24.08.14.
 */
public class SOToFile {
    public static void main(String[] args) throws FileNotFoundException {
        File file = new File ("/Users/kurakinaleksandr/printToFileTest.txt");
        PrintStream out = new PrintStream(file);
        System.setOut(out);

        System.out.println("Test");
    }
}
