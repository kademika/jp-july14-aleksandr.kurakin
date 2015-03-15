package com.kademika.shop.network;

import com.kademika.shop.entitys.Customer;
import com.kademika.shop.entitys.Purchase;
import com.kademika.shop.constants.Name;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kurakinaleksandr on 19.01.15.
 */
public class SimpleClient {
    static Socket socket;
    ObjectOutputStream clientOut;
    ObjectInputStream clientIn;

    String str = "SELECT  purchases.purchaseID, customers.name, bird_storage.type_name, purchases.quantity,purchases.purchase_date FROM purchases" +
            "" +
            "  INNER JOIN customers " +
            "    ON purchases.customerID=customers.customersID" +
            "  inner join bird_storage" +
            "  on purchases.birdID=bird_storage.birdID";

    public void SimpleClient() {

    }

    public void startClient() throws IOException, ClassNotFoundException {

        new Thread(new Runnable() {
            @Override
            public void run() {

                try {

                    socket = new Socket("localhost", 8080);

                    clientOut = new ObjectOutputStream(socket.getOutputStream());
                    clientIn = new ObjectInputStream(socket.getInputStream());

                    System.out.println("Client started and ready!");

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    public void makePurchase(Purchase prch) {
        try {
            clientOut.writeObject(prch);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getReport(String report) {

        List<Customer> collection = new ArrayList();
        try {
            clientOut.writeObject(report);
            clientOut.flush();


            int packageLength = clientIn.readInt();
            for (int i = 0; i < packageLength; i++) {
                collection.add((Customer) clientIn.readObject());
            }

        } catch (IOException
                | ClassNotFoundException
                e) {
            e.printStackTrace();
        }
        printCustomers(collection);
    }

    public Name[] getCatalog() {

        Name[] catalog = {Name.CHICKEN, Name.DUCK, Name.EAGLE, Name.OWL, Name.PARROT, Name.CANARY}; // do not use yet

        return catalog;
    }

    // submethod for getAllCustomers
    public static void printCustomers(List<Customer> customers) {
        System.out.println("--------------");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println(customers.get(i).getName());
        }
    }

    // refactor
    public String[] getCommands() {
        String[] result = {"Get price list", "Get all clients", "Get today transactions", "Get 7-days transactions"};
        return result;
    }
//        List<Customer> collection = new ArrayList();

//        try {

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
//      try {
//              ObjectOutputStream clientOut = new ObjectOutputStream(socket.getOutputStream());
//           }catch (IOException e){
//               e.printStackTrace();
//           }
//        printCustomers(collection);

    //            System.out.println("Client asks server 0 - send list of customers");
//            clientOut.writeChar(0);
//            clientOut.flush();
//            int collectionSize = clientIn.readInt();
//            for (int k = 0; k < collectionSize; k++) {
//                collection.add((Customer) clientIn.readObject());
//            }
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }


    //        Command getCatalog = new Command(); //??
//        getCatalog.setCommand(Commands.GETCATALOG); //??

//        try {
//            clientOut.writeObject(getCatalog);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
}
