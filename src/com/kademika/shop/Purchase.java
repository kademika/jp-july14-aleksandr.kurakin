package com.kademika.shop;

import java.util.Random;

import com.kademika.shop.constants.Name;

public class Purchase {
	private int numberOfBirds;
	private String customerName;
	private int purchaseTime;
	Bird[] prchs;

	public Purchase(String customerName, Name name, int number) {
		this.customerName = customerName;
		Random r = new Random();
		this.purchaseTime = r.nextInt(6);
		prchs = new Bird[number];
		this.numberOfBirds = number;
	}

	public void addBirdToPurchase(Bird bird) {

		for (int i = 0; i < numberOfBirds; i++) {
			if (prchs[i] == null) {
				prchs[i] = bird;
				break;
			}
		}
	}

	public String getCustomer() {
		return customerName;
	}

	public int getPurchaseTime() {
		return purchaseTime;
	}

	public Bird[] getPrchs() {
		return prchs;
	}
	
	public int getNumberOfBirds(){
		return numberOfBirds;
	}
}
