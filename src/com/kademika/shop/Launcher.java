package com.kademika.shop;

import com.kademika.shop.constants.Name;

import java.io.IOException;


public class Launcher {

    public static void main(String[] args) throws IOException {
        Shop shop = new Shop();
        FillBirdStorage fillBirdStorage = new FillBirdStorage(shop);

        UserInterface shopUI = new UserInterface(shop);

    }


}

