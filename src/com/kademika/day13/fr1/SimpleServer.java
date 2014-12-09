package com.kademika.day13.fr1;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kurakinaleksandr on 07.12.14.
 */
public class SimpleServer {

    public static void main(String[] args) throws Exception {
        ServerSocket ss = new ServerSocket(8080);
        while (true) {
            Socket socket = ss.accept();
            try (
                    InputStream in = socket.getInputStream();
                    OutputStream out = socket.getOutputStream()
            ) {
                int data;
                while ((data = in.read()) != -1) {
                    data = transmogrify(data);
                    out.write(data);
                }
            }
        }

    }

    public static int transmogrify(int data) {
        if (Character.isLetter(data)) {
            return  data ^ ' ';
        }
        return data;
    }
}
