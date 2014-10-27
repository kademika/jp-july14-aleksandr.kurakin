package com.kademika.shop;

import com.kademika.shop.constants.Name;

import java.util.ArrayList;
import java.util.List;

// Storages hranit infomatciu o tovarah, pokupatel'ah i pokupkah
public class Storages {

    Bird[][] brdStrg;
    private PurchaseStorage prchsStrg;

    class PurchaseStorage<T extends Purchase> {
        private  List<T> items  = new ArrayList<>();;


        public void PurchaseStorage() {

        }

//        public <T> T getPurchase(int index) {
//            return (T) items.get(index);
//        }

        public void add(T item) {       //adding a purchase
            items.add(item);
        }

        public List<T> getPurchases() {  //list of all purchases
            return new ArrayList<T>(items);
        }
    }

    public Storages() {
        brdStrg = new Bird[Name.values().length][100];

        prchsStrg = new PurchaseStorage();
    }


    // vynimanie pticy iz hranilischa
    public Bird getBird(Name name) {
        Bird brd = null;
        if (brdStrg[name.getId()][0] == null) {
            System.out.println("Sorry, we have no this birds anymore =(");// insert Exception here,
        }
        for (int i = 0; i < 100; i++) {
            if (brdStrg[name.getId()][i] == null) {
                brd = brdStrg[name.getId()][i - 1];
                brdStrg[name.getId()][i - 1] = null;
                break;
            }
        }
        return brd;
    }

    //rabotaem s pokupatelem, zdes' est vozmognost' tol'ko dobavleniya
    public void insertPurchase(Purchase prchs) {
        prchsStrg.add(prchs);
    }

    public List<Purchase> purchaseInStorage() {

        return prchsStrg.getPurchases();
    }


    public Bird birdInStorage(Name name, int number) {
        final Bird brd = brdStrg[name.getId()][number]; // is it correct?
        return brd;
    }

    // rabotaem s pticami (tovar)
    // dibavlenie novoi pticy v hranilische
    public void insertBird(Bird bird) {
        for (int i = 0; i < 100; i++) {
            if (brdStrg[bird.getName().getId()][i] == null) {
                brdStrg[bird.getName().getId()][i] = bird;
                break;
            }
        }
    }

}
