package com.kademika.shop;

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
    Storages strgs;
    Purchase prch;
    Shop shop = null;

    public void ShopServer() {

    }

    public void setShop(Shop shop){
        this.shop = shop;
    }

    public void start(){
        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    ServerSocket ss = new ServerSocket(8080);
                    Socket socket = ss.accept();

                    ObjectInputStream serverIn = new ObjectInputStream(socket.getInputStream());
                    ObjectOutputStream serverOut = new ObjectOutputStream(socket.getOutputStream());

                    while (true) {

                        // check inStream object
                        Object start = serverIn.readObject();

                        if (start instanceof Purchase) {
                            System.out.println("Server got new purchase");
                            prch = (Purchase) start;
                            shop.makePurchase(prch.getCustomer(), prch.getName(), prch.getNumberOfBirds());
                        } else
                            if(start instanceof Command){
                                String command = ((Command) start).getCommand();

                            }
                    }

                } catch (IOException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

}
//                            int collectionSize = cstmrList.size();
//                            serverOut.writeInt(collectionSize);
//                            for (int i = 0; i < collectionSize; i++) {
//                                serverOut.writeObject(cstmrList.get(i));
//                            }
//                            System.out.println("Data transfer complete");
//                            serverOut.flush();