package com.kademika.day13.fr1_6;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.ConcurrentLinkedDeque;

/**
 * Created by kurakinaleksandr on 14.12.14.
 */
public class NonblockingSelectorServer {

    private static Map<SocketChannel, Queue<ByteBuffer>> pendingData = new HashMap<>();

    public static void main(String[] args) throws IOException {


        ServerSocketChannel ssc = ServerSocketChannel.open();
        ssc.bind(new InetSocketAddress("localhost", 8080));
        ssc.configureBlocking(false);

        Selector selector = Selector.open();
        ssc.register(selector, SelectionKey.OP_ACCEPT);

        while (true) {
            selector.select();
            for(Iterator<SelectionKey> keyIterator = selector.selectedKeys().iterator(); keyIterator.hasNext();){
                SelectionKey key = keyIterator.next();
                keyIterator.remove();

                if(key.isValid()) {
                    if (key.isAcceptable()) {
                        accept(key);
                    } else if (key.isReadable()) {
                        read(key);
                    } else if (key.isWritable()) {
                        write(key);
                    }
                }
            }
        }
    }

    private static void write(SelectionKey key) throws IOException{
        SocketChannel sc =(SocketChannel) key.channel();
        Queue <ByteBuffer> queue = pendingData.get(sc);
        ByteBuffer buffer;

        while ((buffer = queue.peek()) !=null){
            sc.write(buffer);
            if (!buffer.hasRemaining()){
                queue.poll();
            } else return;
        }
        sc.register(key.selector(), SelectionKey.OP_READ);
    }

    private static void read(SelectionKey key) throws IOException {
        SocketChannel sc = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocateDirect(1024);

        int read = sc.read(buffer);
        if(read == -1){
            pendingData.remove(sc);
            return;
        }
        buffer.flip();
        for (int i = 0; i<buffer.limit(); i++){
            buffer.put(i, (byte) transmogrify(buffer.get(i)));
        }
        pendingData.get(sc).add(buffer);
        sc.register(key.selector(), SelectionKey.OP_WRITE);
    }

    private static void accept(SelectionKey key) throws IOException {
    ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
        SocketChannel sc = ssc.accept();
        sc.configureBlocking(false);

        sc.register(key.selector(), SelectionKey.OP_READ);
        pendingData.put(sc, new ConcurrentLinkedDeque<ByteBuffer>());
    }

    public static int transmogrify(int data) {
        if (Character.isLetter(data)) {
            return data ^ ' ';
        }
        return data;
    }
}
