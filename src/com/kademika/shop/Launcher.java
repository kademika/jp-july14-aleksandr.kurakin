package com.kademika.shop;

import com.kademika.shop.constants.Name;


public class Launcher {

    public static void main(String[] args) {
        Shop shop = new Shop();
        setUpProducts(shop);
        UserInterface shopUI = new UserInterface(shop);

    }

    private static void setUpProducts(Shop shop) {

        shop.insertBird(new Bird(Name.CHICKEN, 6.1));
        shop.insertBird(new Bird(Name.CANARY, 10.3));
        shop.insertBird(new Bird(Name.DUCK, 8.0));
        shop.insertBird(new Bird(Name.EAGLE, 17.1));
        shop.insertBird(new Bird(Name.PARROT, 12.3));
        shop.insertBird(new Bird(Name.OWL, 15.7));

        shop.insertBird(new Bird(Name.CHICKEN, 6.1));
        shop.insertBird(new Bird(Name.CANARY, 10.3));
        shop.insertBird(new Bird(Name.DUCK, 8.0));
        shop.insertBird(new Bird(Name.EAGLE, 17.1));
        shop.insertBird(new Bird(Name.PARROT, 12.3));
        shop.insertBird(new Bird(Name.OWL, 15.7));

        shop.insertBird(new Bird(Name.CHICKEN, 6.1));
        shop.insertBird(new Bird(Name.CANARY, 10.3));
        shop.insertBird(new Bird(Name.DUCK, 8.0));
        shop.insertBird(new Bird(Name.EAGLE, 17.1));
        shop.insertBird(new Bird(Name.PARROT, 12.3));
        shop.insertBird(new Bird(Name.OWL, 15.7));

        shop.insertBird(new Bird(Name.CHICKEN, 6.1));
        shop.insertBird(new Bird(Name.CANARY, 10.3));
        shop.insertBird(new Bird(Name.DUCK, 8.0));
        shop.insertBird(new Bird(Name.EAGLE, 17.1));
        shop.insertBird(new Bird(Name.PARROT, 12.3));
        shop.insertBird(new Bird(Name.OWL, 15.7));

        shop.insertBird(new Bird(Name.CHICKEN, 6.1));
        shop.insertBird(new Bird(Name.CANARY, 10.3));
        shop.insertBird(new Bird(Name.DUCK, 8.0));
        shop.insertBird(new Bird(Name.EAGLE, 17.1));
        shop.insertBird(new Bird(Name.PARROT, 12.3));
        shop.insertBird(new Bird(Name.OWL, 15.7));

        shop.insertBird(new Bird(Name.CHICKEN, 6.1));
        shop.insertBird(new Bird(Name.CANARY, 10.3));
        shop.insertBird(new Bird(Name.DUCK, 8.0));
        shop.insertBird(new Bird(Name.EAGLE, 17.1));
        shop.insertBird(new Bird(Name.PARROT, 12.3));
        shop.insertBird(new Bird(Name.OWL, 15.7));

//        shop.makePurchase("Aleksandr", Name.CHICKEN, 2);
//        shop.makePurchase("Aleksandr", Name.CANARY, 1);
//        shop.makePurchase("Anna", Name.EAGLE, 1);
//        shop.makePurchase("Oleg", Name.OWL, 2);
//        shop.makePurchase("Anna", Name.DUCK, 1);
//        shop.makePurchase("Anna", Name.PARROT, 2);
//        shop.makePurchase("Anna", Name.CANARY, 1);
    }

}

