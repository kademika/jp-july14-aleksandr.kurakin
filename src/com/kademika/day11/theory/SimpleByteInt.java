package com.kademika.day11.theory;

/**
 * Created by kurakinaleksandr on 11.08.14.
 */
public class SimpleByteInt {

    static byte b;
    public static void main(String[] args) {
        for(int i = -257; i<257; i++){
            b = (byte) i;
            System.out.print(b + " ");
            System.out.print(i + " ");
            System.out.println("");
        }
    }
}
