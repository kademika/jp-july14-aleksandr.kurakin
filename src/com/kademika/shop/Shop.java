package com.kademika.shop;

import com.kademika.shop.DAO.BirdDao;
import com.kademika.shop.DAO.CustomerDao;
import com.kademika.shop.DAO.PurchaseDao;
import com.kademika.shop.constants.Name;
import com.kademika.shop.entitys.Customer;
import com.kademika.shop.entitys.Purchase;
import com.kademika.shop.network.ShopServer;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Shop {
//    static private Storages strg;
    Purchase prchs;
    Customer cstmr;
    ShopServer ss;


//    public  void startServer() throws IOException {
//
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//
//                    ServerSocket ss = new ServerSocket(8080);
//                    Socket socket = ss.accept();
//
//                    ObjectInputStream serverIn = new ObjectInputStream(socket.getInputStream());
//                    ObjectOutputStream serverOut = new ObjectOutputStream(socket.getOutputStream());
//
//                    while (true) {
//                        Character start = serverIn.readChar();
//                        if (start == 0) {
//                            System.out.println("Server got request for list of customers from client = 0");
//                            List<Customer> cstmrList = strg.getCustomers();
//                            int collectionSize = cstmrList.size();
//                            serverOut.writeInt(collectionSize);
//                            for (int i = 0; i < collectionSize; i++) {
//                                serverOut.writeObject(cstmrList.get(i));
//                            }
//                            System.out.println("Data transfer complete");
//                            serverOut.flush();
//                        }
//                    }
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
//
//    }

    public Shop() throws IOException {
//        strg = new Storages();
        ss = new ShopServer();
        ss.setShop(this);
        ss.start();
//        new Thread(new Runnable() {
//            @Override
//            public void run() {
//
//                try {
//
//                    ServerSocket ss = new ServerSocket(8080);
//                    Socket socket = ss.accept();
//
//                    ObjectInputStream serverIn = new ObjectInputStream(socket.getInputStream());
//                    ObjectOutputStream serverOut = new ObjectOutputStream(socket.getOutputStream());
//
//                    while (true) {
//                        Character start = serverIn.readChar();
//                        if (start == 0) {
//                            System.out.println("Server got request for list of customers from client = 0");
//                            List<Customer> cstmrList = strg.getCustomers();
//                            int collectionSize = cstmrList.size();
//                            serverOut.writeInt(collectionSize);
//                            for (int i = 0; i < collectionSize; i++) {
//                                serverOut.writeObject(cstmrList.get(i));
//                            }
//                            System.out.println("Data transfer complete");
//                            serverOut.flush();
//                        }
//                    }
//
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }
//            }
//        }).start();
    }

    //OK
    public void makePurchase(String customerName, Name birdName, int number) {
        BirdDao bd = new BirdDao();
        CustomerDao cd = new CustomerDao();
        PurchaseDao pd = new PurchaseDao();
        int actualBirdBalance = bd.getBirdBalance(birdName);
        System.out.println("The balance of " + birdName + " in storage is: " + actualBirdBalance);

        if (number <= actualBirdBalance) {
            // create and add customer in Storages
            cstmr = new Customer();
            cstmr.setName(customerName);

            cd.insertCustomer(cstmr);
            // create and add purchase in Storages
            prchs = new Purchase(customerName, birdName, number);

            pd.insertPurchase(prchs);
            actualBirdBalance = bd.getBirdBalance(birdName);
            //
            System.out.println(customerName + " bout " + number + " " + birdName + "s.");
            System.out.println("New balance of " + birdName + " in storage is: " + actualBirdBalance);
        } else
            System.out.println("There are only " + actualBirdBalance + " " + birdName + " in storage, please, " +
                    "enter new Quantity - less then " + actualBirdBalance);
    }

    // methods to get reports
//	public void getPriceList(){		//show price list
//		System.out.println("Price list: ");
//		for(int i = 0; i < Name.values().length; i++){
//			if (strg.birdInStorage(Name.values()[i], 0) == null){
//				System.out.println(Name.values()[i] + "temporary not available now, sorry");
//			} else System.out.println(Name.values()[i] + " price is: " + strg.birdInStorage(Name.values()[i], 0).getPrice());
//		}
//	}

//	public void getBirdsInStock (){	// ostatki na sklade
//		System.out.println("We have next birds in our shop:");
//		int sum;
//		for (int i = 0; i < Name.values().length; i++){
//			sum = 0;
//			for (int j = 0; j<100; j++){
//				if (strg.birdInStorage(Name.values()[i], j) != null){
//				sum +=1;
//			} else break;
//
//		}
//			System.out.println(Name.values()[i] + " in stock: " + sum);
//		}
//	}

//	public void getLast7daysTransactions(){		//printing
//		int today = 0;
//		int oneDayAgo = 0;
//		int twoDaysAgo = 0;
//		int threeDaysAgo = 0;
//		int fourDaysAgo = 0;
//		int fiveDaysAgo = 0;
//		int sixDaysAgo = 0;
//		for (int i = 0; i < 1000; i++){
//			if (strg.purchaseInStorage(i) == null){
//				break;
//			} else {
//				switch (strg.purchaseInStorage(i).getPurchaseTime()){
//				case 0: today +=strg.purchaseInStorage(i).getNumberOfBirds(); // or we should make "+1" ??
//				case 1: oneDayAgo +=strg.purchaseInStorage(i).getNumberOfBirds();
//				case 2: twoDaysAgo +=strg.purchaseInStorage(i).getNumberOfBirds();
//				case 3: threeDaysAgo +=strg.purchaseInStorage(i).getNumberOfBirds();
//				case 4: fourDaysAgo +=strg.purchaseInStorage(i).getNumberOfBirds();
//				case 5: fiveDaysAgo +=strg.purchaseInStorage(i).getNumberOfBirds();
//				case 6: sixDaysAgo +=strg.purchaseInStorage(i).getNumberOfBirds();
//				}
//			}
//		}
//		System.out.println("Last seven days, from today: "
//		+ today +", "+ oneDayAgo +", "+ twoDaysAgo +", "+ threeDaysAgo +", "+ fourDaysAgo +", "+ fiveDaysAgo +", "+ sixDaysAgo);
//	}

    // transactions today
//	public void getTodayTransactions(){
//		System.out.println("№   Customer    Bird   Price   Amount");
//		System.out.println("_____________________________________");
//		int n = 1;
//		double priceTotal = 0;
//		int amountTotal = 0;
//		for (int i=0; i<1000; i++){
//			if (strg.purchaseInStorage(i) != null && strg.purchaseInStorage(i).getPurchaseTime() == 0){
//				System.out.println(n + ", "+ strg.purchaseInStorage(i).getCustomer() + ", " +
//						strg.purchaseInStorage(i).getPrchs()[0].getName() + ", " +
//						strg.purchaseInStorage(i).getPrchs()[0].getPrice() + ", "+ strg.purchaseInStorage(i).getNumberOfBirds());
//				n +=1;
//				priceTotal += strg.purchaseInStorage(i).getPrchs()[0].getPrice();
//				amountTotal += strg.purchaseInStorage(i).getNumberOfBirds();
//			}
//		}
//		System.out.println("Totaly: "+ n + " purchases  "+ priceTotal + "  " + amountTotal);
//	}

//    Get customers
    public List<Customer> getCustomers() {
        List customers = new ArrayList();
        CustomerDao cd = new CustomerDao();
        customers = cd.getAllCustomers();
        return customers;
    }

    // Catalog with categories. Add Category column in BirdsDB
    public Name[] getCatalog() {

        int catalogLength = Name.values().length;
        Name[] catalog = new Name[catalogLength];
        for (int i = 0; i < Name.values().length; i++) {
            catalog[i] = Name.values()[i];
        }

        return catalog;
    }
}
