package com.kademika.day13.fr1_6;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.*;

/**
 * Created by kurakinaleksandr on 14.12.14.
 */
public class TPServer {

    public static void main(String[] args) throws Exception {
        final ServerSocket ss = new ServerSocket(8080);
        ExecutorService pool = new ThreadPoolExecutor(0, 1000, 60L, TimeUnit.SECONDS,
                new SynchronousQueue<Runnable>(), new ThreadPoolExecutor.DiscardPolicy());


        while (true) {
            final Socket socket = ss.accept();
            pool.submit(new  Runnable() {
                @Override
                public void run() {
                    try {
                        newConnection(socket);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private static void newConnection(Socket socket) throws IOException {

        System.out.println("New connection from " + socket);
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
