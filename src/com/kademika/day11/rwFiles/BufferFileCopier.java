package com.kademika.day11.rwFiles;

import java.io.*;

/**
 * Created by kurakinaleksandr on 21.08.14.
 */
public class BufferFileCopier {
    public static void main(String[] args) {

        File source = new File("/Users/kurakinaleksandr/L1050035.JPG");
        try {
            copyFile(source);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

    }


    public static void copyFile(File src) throws FileNotFoundException {
        File result = new File(src.getParent() + "/Copy of " + src.getName());
        int size;
        int buffLength = 256;
        try (BufferedInputStream bis = new BufferedInputStream(new FileInputStream(src), buffLength);
              BufferedOutputStream  bos = new BufferedOutputStream(new FileOutputStream(result), buffLength)){
            size = bis.available();
            for (int i = 0; i < size; i++) {
                bos.write(bis.read());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
