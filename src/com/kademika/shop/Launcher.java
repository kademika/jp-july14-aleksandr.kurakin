package com.kademika.shop;

import com.kademika.shop.constants.Name;


public class Launcher {

    public static void main(String[] args) {
        Shop shop = new Shop();
        FillBirdStorage fillBirdStorage = new FillBirdStorage(shop);

        UserInterface shopUI = new UserInterface(shop);

    }


}

