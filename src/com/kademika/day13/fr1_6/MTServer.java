package com.kademika.day13.fr1_6;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kurakinaleksandr on 13.12.14.
 */
public class MTServer {
    public static void main(String[] args) throws Exception {
        final ServerSocket ss = new ServerSocket(8080);
        while (true) {
            final Socket socket = ss.accept();
            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        newConnection(socket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }).start();
        }
    }

    private static void newConnection(Socket socket) throws IOException {

        System.out.println("New connection");
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


    public static int transmogrify(int data) {
        if (Character.isLetter(data)) {
            return data ^ ' ';
        }
        return data;
    }
}
