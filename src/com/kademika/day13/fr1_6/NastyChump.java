package com.kademika.day13.fr1_6;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Created by kurakinaleksandr on 14.12.14.
 */
public class NastyChump {
    public static void main(String[] args) throws InterruptedException {
        for (int i = 0; i<3000; i++){
            try{
                new Socket("localhost", 8080);
                System.out.println(i);
            } catch (UnknownHostException e) {
                e.printStackTrace();
            } catch (IOException e) {
                System.out.println("Could not connect - "+ e);
            }
        }
        Thread.sleep(100000);
    }
}
