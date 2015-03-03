package com.kademika.shop.entitys;

import java.io.Serializable;
import java.util.*;

import com.kademika.shop.constants.Name;

public class Purchase implements Serializable{
	private int numberOfBirds;
    private Name name;
	private String customerName;
	private Date purchaseTime;
	List <Bird> prchs = new ArrayList();

	public Purchase(String customerName, Name name, int number) {
		this.customerName = customerName;
        this.name = name;
		Random r = new Random();
		this.purchaseTime = new Date(Calendar.getInstance().getTimeInMillis());
//		prchs = new Bird[number];
		this.numberOfBirds = number;
	}

	public void addBirdToPurchase(Bird bird) {
        prchs.add(bird);
	}

	public String getCustomer() {
		return customerName;
	}

	public Date getPurchaseTime() {
		return purchaseTime;
	}

	public List getPrchs() {
		return prchs;
	}
	
	public int getNumberOfBirds(){
		return numberOfBirds;
	}

    public Name getName() {
        return name;
    }
}
