package com.kademika.shop;

import com.kademika.shop.constants.Name;
import com.kademika.shop.constants.Type;

import javax.swing.*;
import java.awt.*;
import java.awt.image.ImageObserver;
import java.util.ArrayList;
import java.util.*;
import java.util.List;

public class Shop {
	private Storages strg;


	public Shop() {
		strg = new Storages();



	}
	
	public void insertBird (Bird bird){
		strg.insertBird(bird);
	}

	public void makePurchase(String customerName, Name birdName, int number) {
		
		Purchase prchs = new Purchase (customerName,birdName, number);
		for (int i = 0; i < number; i++){
			prchs.addBirdToPurchase(strg.getBird(birdName));
		}
		strg.insertPurchase(prchs);
	}
	// methods to get reports
	public void getPriceList(){		//show price list
		System.out.println("Pricelist: ");
		for(int i = 0; i < Name.values().length; i++){
			if (strg.birdInStorage(Name.values()[i], 0) == null){
				System.out.println(Name.values()[i] + "temporary not available now, sorry");
			} else System.out.println(Name.values()[i] + " price is: " + strg.birdInStorage(Name.values()[i], 0).getPrice());
		}
	}
	
	public void getBirdsInStock (){	// ostatki na sklade
		System.out.println("We have next birds in our shop:");
		int sum;
		for (int i = 0; i < Name.values().length; i++){
			sum = 0;
			for (int j = 0; j<100; j++){
				if (strg.birdInStorage(Name.values()[i], j) != null){
				sum +=1;
			} else break;
			
		}
			System.out.println(Name.values()[i] + " in stock: " + sum);
		}
	}
	
	public void getLast7daysTransactions(){		//printing 
		int today = 0;
		int oneDayAgo = 0;
		int twoDaysAgo = 0;
		int threeDaysAgo = 0;
		int fourDaysAgo = 0;
		int fiveDaysAgo = 0;
		int sixDaysAgo = 0;
		for (int i = 0; i < 1000; i++){
			if (strg.purchaseInStorage(i) == null){
				break;
			} else {
				switch (strg.purchaseInStorage(i).getPurchaseTime()){
				case 0: today +=strg.purchaseInStorage(i).getNumberOfBirds(); // or we should make "+1" ??
				case 1: oneDayAgo +=strg.purchaseInStorage(i).getNumberOfBirds();
				case 2: twoDaysAgo +=strg.purchaseInStorage(i).getNumberOfBirds();
				case 3: threeDaysAgo +=strg.purchaseInStorage(i).getNumberOfBirds();
				case 4: fourDaysAgo +=strg.purchaseInStorage(i).getNumberOfBirds();
				case 5: fiveDaysAgo +=strg.purchaseInStorage(i).getNumberOfBirds();
				case 6: sixDaysAgo +=strg.purchaseInStorage(i).getNumberOfBirds();
				}
			}
		}
		System.out.println("Last seven days, from today: " 
		+ today +", "+ oneDayAgo +", "+ twoDaysAgo +", "+ threeDaysAgo +", "+ fourDaysAgo +", "+ fiveDaysAgo +", "+ sixDaysAgo);
	}
	
	// transactions today
	public void getTodayTransactions(){
		System.out.println("№   Customer    Bird   Price   Amount");
		System.out.println("_____________________________________");
		int n = 1;
		double priceTotal = 0;
		int amountTotal = 0;
		for (int i=0; i<1000; i++){
			if (strg.purchaseInStorage(i) != null && strg.purchaseInStorage(i).getPurchaseTime() == 0){
				System.out.println(n + ", "+ strg.purchaseInStorage(i).getCustomer() + ", " +
						strg.purchaseInStorage(i).getPrchs()[0].getName() + ", " + 
						strg.purchaseInStorage(i).getPrchs()[0].getPrice() + ", "+ strg.purchaseInStorage(i).getNumberOfBirds());
				n +=1;
				priceTotal += strg.purchaseInStorage(i).getPrchs()[0].getPrice();
				amountTotal += strg.purchaseInStorage(i).getNumberOfBirds();
			}
		}
		System.out.println("Totaly: "+ n + " purchases  "+ priceTotal + "  " + amountTotal);
	}
	
	// Catalog with categories
	public String[]  getCatalog (){
        int catalogLength = Name.values().length;
        String[] catalog = new String [catalogLength];
        for (int i = 0; i<Name.values().length; i++){
        catalog[i] = Name.values()[i].toString();
        }

        return catalog;
	}
}
