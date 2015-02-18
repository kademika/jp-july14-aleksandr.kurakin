package com.kademika.shop;



import java.io.IOException;


public class Launcher {

    public static void main(String[] args) throws IOException {
        Shop shop = new Shop();
//        FillBirdStorage.fillBirdStorage(shop);

        UserInterface shopUI = new UserInterface(shop);

    }


}

