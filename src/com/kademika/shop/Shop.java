package com.kademika.shop;

import com.kademika.shop.DAO.BirdDao;
import com.kademika.shop.DAO.CustomerDao;
import com.kademika.shop.DAO.PurchaseDao;
import com.kademika.shop.constants.Name;
import com.kademika.shop.entitys.Bird;
import com.kademika.shop.entitys.Customer;
import com.kademika.shop.entitys.Purchase;
import com.kademika.shop.network.ShopServer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Shop {

    private Purchase prchs;
    private Customer cstmr;
    private ShopServer ss;
    private BirdDao bd;
    private CustomerDao cd;
    private PurchaseDao pd;


    public Shop() throws IOException {

        ss = new ShopServer();
        ss.setShop(this);
        ss.start();
        bd = new BirdDao();
        cd = new CustomerDao();
        pd = new PurchaseDao();
    }

    //OK
    public void makePurchase(Purchase purchase) {

        String customerName = purchase.getCustomer();
        String birdName = purchase.getName();
        int number = purchase.getNumberOfBirds();
        int actualBirdBalance = bd.getBirdBalance(birdName);
        System.out.println("The balance of " + birdName + " in storage is: " + actualBirdBalance);

        if (number <= actualBirdBalance) {
            // Check if customer already present in DB

            List<Customer> customers = cd.getAllCustomers();
            int count = 0;
            for (Customer cstmr:customers) {
                if(cstmr.getName().equals(customerName)) {
                    count++;
                }
            }
            if (count==0){
                // create and add customer in Storages
                cstmr = new Customer();
                cstmr.setName(customerName);
                cd.insertCustomer(cstmr);
            }
            else {
                System.out.println(customerName + " welcome again!");
            }
            // create and add purchase in Storages
            prchs = new Purchase(customerName, birdName, number);

            pd.insertPurchase(prchs);
            bd.birdStrgUpdate(prchs);
            actualBirdBalance = bd.getBirdBalance(birdName);

            System.out.println(customerName + " bout " + number + " " + birdName + "s.");
            System.out.println("New balance of " + birdName + " in storage is: " + actualBirdBalance);
        } else
            System.out.println("There are only " + actualBirdBalance + " " + birdName + " in storage, please, " +
                    "enter new Quantity - less then " + actualBirdBalance);
    }
    //    Get customers
    public List<String> getCustomers() {
        List<Customer> customers= cd.getAllCustomers();
        List<String> result = new ArrayList<>(customers.size());
        for (int i=0; i<customers.size(); i++){
            result.add(customers.get(i).getName()) ;
        }
        return result;
    }

    // Catalog with categories. Add Category column in BirdsDB
    public List<String> getCatalog() {
        List<Bird> birds = bd.getCatalog();
        List<String> result = new ArrayList<>(birds.size());
        for(int i=0; i<birds.size(); i++){
            result.add(birds.get(i).getName());
        }
        return result;
    }

}
