package com.kademika.day13.fr1_6;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.*;

/**
 * Created by kurakinaleksandr on 14.12.14.
 */
public class NIOServer {
    public static void main(String[] args) throws Exception {
        ServerSocketChannel ssc =  ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress("localhost", 8080));
        ExecutorService pool = Executors.newFixedThreadPool(1000);


        while (true) {
            final SocketChannel socketChannel = ssc.accept();
            pool.submit(new  Runnable() {
                @Override
                public void run() {
                    try {
                        newConnection(socketChannel);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }

    private static void newConnection(SocketChannel socket) throws IOException {
        System.out.println("New connection from " + socket);

        try
        {
            ByteBuffer buffer = ByteBuffer.allocate(1024);
            // position == 0
            // limit == capacity = 1024
            while (socket.read(buffer) != -1){
                //byte array with additional info
                // position
                // limit
                // capacity

                // hello

//                buffer.limit(buffer.position());
//                buffer.position(0);

                // or

                buffer.flip();
                for (int i = 0; i < buffer.limit(); i++){
                    buffer.put(i, (byte) transmogrify(buffer.get(i)));
                }
                socket.write(buffer);
                buffer.clear();
            }
        } catch (IOException e){
            System.out.println("Connection problem - " + e);
        }

    }


    public static int transmogrify(int data) {
        if (Character.isLetter(data)) {
            return data ^ ' ';
        }
        return data;
    }
}
