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

        Socket socket = new Socket("localhost", 8080);
        List<Customer> collection = new ArrayList();

        try (
                ObjectOutputStream clientOut = new ObjectOutputStream(socket.getOutputStream());
                ObjectInputStream clientIn = new ObjectInputStream(socket.getInputStream());
        ) {
            System.out.println("Client asks server 3 - send list of customers");
            clientOut.writeChar(0);

            if (clientIn.readChar() == 1) {

                while (clientIn.readChar() != 2) {
                    collection.add((Customer) clientIn.readObject());

                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

        printCustomers(collection);
    }

    public static void printCustomers(List<Customer> customers){
        for(int i = 0; i < customers.size(); i++){
            System.out.println(customers.get(i).getName());
        }
    }
}
