package com.kademika.day11.theory;

import java.io.ByteArrayInputStream;

/**
 * Created by kurakinaleksandr on 11.08.14.
 */
public class BAISReader {
    public static void main(String[] args) {
        byte [] bytes = {1, 34, 45, 24,19, 94, 18, -34, 45, -120, 34};
        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
        printStreamData(inputStream);
//        byte[] bytes = {1, 34, 45, 24, 19, 94, 18, -34, 45, -120, 34};
//        ByteArrayInputStream inputStream = new ByteArrayInputStream(bytes);
//        int i;
//        while ((i = inputStream.read()) != -1) {
//            System.out.print((byte)i);
//            System.out.print(" ");
//        }
//    }
    }

    public static void printStreamData(ByteArrayInputStream byteArrayInputStream){
                int i;
        while((i=byteArrayInputStream.read()) != -1){
            System.out.print((byte)i);
            System.out.print(" ");
        }
    }
}
