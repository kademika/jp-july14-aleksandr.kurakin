package com.kademika.day11.theory;


import java.io.*;

public class BufferReader implements FileReader {

    @Override
    public String read(String fileName) {
        StringBuilder builder = new StringBuilder();

        try (

                BufferedInputStream bis = new BufferedInputStream(new FileInputStream(fileName))
        ) {
            int i;
            while ((i = bis.read()) != -1) {
                builder.append((char) i);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return builder.toString();
    }


}
