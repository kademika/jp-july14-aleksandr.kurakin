package com.kademika.day11.theory;


import java.io.*;


public class FilePrinter {
    public static void main(String[] args) {
        File file = new File("/Users/kurakinaleksandr/Music.txt");
        InputStream inputStream = null;
        try {
             inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        printStreamData(inputStream);
//
    }

    public static void printStreamData(InputStream inputStream){
        int i;
        try {
            while((i=inputStream.read()) != -1){
                System.out.print((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
