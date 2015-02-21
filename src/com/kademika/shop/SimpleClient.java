package com.kademika.shop;

import com.kademika.shop.constants.Name;

import java.io.*;
import java.lang.reflect.Array;
import java.net.Socket;
import java.util.List;

import static com.kademika.shop.constants.Name.CHICKEN;

/**
 * Created by kurakinaleksandr on 19.01.15.
 */
public class SimpleClient {
static Socket socket;
    ObjectOutputStream clientOut;

   public void SimpleClient(){

   }

    public  void startClient() throws IOException, ClassNotFoundException {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {
//        List<Customer> collection = new ArrayList();

//        try {
            socket = new Socket("localhost", 8080);

            clientOut = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream clientIn = new ObjectInputStream(socket.getInputStream());

            System.out.println("Client started and ready!");

//            System.out.println("Client asks server 0 - send list of customers");
//            clientOut.writeChar(0);
//            clientOut.flush();
//            int collectionSize = clientIn.readInt();
//            for (int k = 0; k < collectionSize; k++) {
//                collection.add((Customer) clientIn.readObject());
//            }
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//try {
//    ObjectOutputStream clientOut = new ObjectOutputStream(socket.getOutputStream());
//}catch (IOException e){
//    e.printStackTrace();
//}
//        printCustomers(collection);

                } catch (IOException  e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void makePurchase (Purchase prch){
        try {
            clientOut.writeObject(prch);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Name [] getCatalog(){
        Name[] catalog = {Name.CHICKEN, Name.DUCK, Name.EAGLE, Name.OWL, Name.PARROT, Name.CANARY}; // do not use yet

        return catalog;
    }

    public static void printCustomers(List<Customer> customers) {
        System.out.println("--------------");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println(customers.get(i).getName());
        }
    }
}
