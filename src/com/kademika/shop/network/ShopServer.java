package com.kademika.shop.network;

import com.kademika.shop.entitys.Customer;

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
    ObjectInputStream serverIn;
    ObjectOutputStream serverOut;

//    Storages strgs;
    Purchase prch;
    Shop shop = null;
    Object start;

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
            shop.makePurchase(prch.getCustomer(), prch.getName(), prch.getNumberOfBirds());

        } else if (start instanceof String & start.equals("Get all clients")) {
            System.out.println("Client request new report: " + start);
//            String command = (String) start;

            List<Customer> customers = shop.getCustomers();
            int packetLength = customers.size();

            try {
                serverOut.writeInt(packetLength);

                for (int i = 0; i < customers.size(); i++) {

                    serverOut.writeObject(customers.get(i));

                }
                System.out.println("Data transfer complete");
                serverOut.flush(); // !!!
            }catch (IOException e) {
                    e.printStackTrace();
                }


            }
//           makeCommand(command);
        }


    public void makeCommand (String command){
//        Commands cmnd = command.getCommand();
        if (command.equals("Get all clients")){

        }
    }

}
//                            int collectionSize = cstmrList.size();
//                            serverOut.writeInt(collectionSize);
//                            for (int i = 0; i < collectionSize; i++) {
//                                serverOut.writeObject(cstmrList.get(i));
//                            }
//                            System.out.println("Data transfer complete");
//                            serverOut.flush();