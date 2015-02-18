package com.kademika.shop;

import java.io.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kurakinaleksandr on 19.01.15.
 */
public class SimpleClient {

    public static void main(String[] args) throws IOException {
        List<Customer> collection = new ArrayList();
        try {
            Socket socket = new Socket("localhost", 8080);

            ObjectOutputStream clientOut = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream clientIn = new ObjectInputStream(socket.getInputStream());

            System.out.println("Client asks server 0 - send list of customers");
            clientOut.writeChar(0);
            clientOut.flush();
            int collectionSize = clientIn.readInt();
            for (int k = 0; k < collectionSize; k++) {
                collection.add((Customer) clientIn.readObject());
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        printCustomers(collection);
    }

    public static void printCustomers(List<Customer> customers) {
        System.out.println("--------------");
        for (int i = 0; i < customers.size(); i++) {
            System.out.println(customers.get(i).getName());
        }
    }
}
