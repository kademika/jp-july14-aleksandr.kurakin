package com.kademika.day11.theory;

import java.io.ByteArrayOutputStream;
import java.util.Arrays;

/**
 * Created by kurakinaleksandr on 11.08.14.
 */
public class BAISWriter {
    public static void main(String[] args) {
        byte[] out = {1, 34, 45, 24,19, 94, 18, -34, 45, -120, 34};
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        for (int i = 0; i<out.length; i++){
            byteArrayOutputStream.write(out[i]);
        }
        System.out.println(Arrays.toString(byteArrayOutputStream.toByteArray()));
    }
}
