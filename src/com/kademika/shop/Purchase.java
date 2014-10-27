package com.kademika.shop;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import com.kademika.shop.constants.Name;

public class Purchase {
	private int numberOfBirds;
	private String customerName;
	private int purchaseTime;
	List <Bird> prchs = new ArrayList();

	public Purchase(String customerName, Name name, int number) {
		this.customerName = customerName;
		Random r = new Random();
		this.purchaseTime = r.nextInt(6);
//		prchs = new Bird[number];
		this.numberOfBirds = number;
	}

	public void addBirdToPurchase(Bird bird) {
        prchs.add(bird);
	}

	public String getCustomer() {
		return customerName;
	}

	public int getPurchaseTime() {
		return purchaseTime;
	}

	public List getPrchs() {
		return prchs;
	}
	
	public int getNumberOfBirds(){
		return numberOfBirds;
	}
}
