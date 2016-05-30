package com.kademika.shop.network;

import com.kademika.shop.entitys.Purchase;
import com.kademika.shop.Shop;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

/**
 * Created by kurakinaleksandr on 19.02.15.
 */
public class ShopServer {
    private static ObjectInputStream serverIn;
    private static ObjectOutputStream serverOut;

    private Purchase prch;
    private Shop shop = null;
    private Object start;
    private static int packetLength=0;
    private List<String> result;


    public void ShopServer() {

    }

    public void setShop(Shop shop) {
        this.shop = shop;
    }

    public void start() {
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    ServerSocket ss = new ServerSocket(8080);
                    Socket socket = ss.accept();

                    serverIn = new ObjectInputStream(socket.getInputStream());
                    serverOut = new ObjectOutputStream(socket.getOutputStream());

                    while (true) {
                        start = serverIn.readObject();
                        checkCommand(start);
                        start = null;
                    }
                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void checkCommand(Object start) { // check inStream object

        if (start instanceof Purchase) {
            System.out.println("Server got new purchase");
            prch = (Purchase) start;
            shop.makePurchase(prch);

        } else if (start instanceof String & start.equals("Get all clients")) {
            System.out.println("Client request new report: " + start);

            result = shop.getCustomers();
            getReport(result);

        } else if (start instanceof String & start.equals("Catalog")) {

            result = shop.getCatalog();
            getReport(result);
        }


    }

    private static void getReport(List<String> result) {
        packetLength = result.size();
        try {
            serverOut.writeInt(packetLength);
            for (int i = 0; i < result.size(); i++) {
                serverOut.writeObject(result.get(i));
            }
            System.out.println("Data transfer complete");
            serverOut.flush(); // !!!
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
