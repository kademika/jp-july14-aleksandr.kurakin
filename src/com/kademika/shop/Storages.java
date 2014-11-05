package com.kademika.shop;

import com.kademika.shop.constants.Name;
import com.kademika.shop.Bird;

import java.util.ArrayList;
import java.util.List;

// Storages hranit infomatciu o tovarah, pokupatel'ah i pokupkah
public class Storages {

    private CustomerStorage customerStorage;
    private PurchaseStorage prchsStrg;
    private BirdStorage birdStorage;

    class PurchaseStorage<T extends Purchase> extends SimpleStorage {

    }

    class CustomerStorage<T extends Customer> extends SimpleStorage {

    }

    class BirdStorage<T extends Bird> extends SimpleStorage {
        Bird brd = null;

        public Bird getBird(Name name) {

            if (getBirdBalance(name) <= 0) {
                System.out.println("Sorry, we have no this birds anymore =(");// insert Exception here,
                brd = null;
            } else

                for (int i = 0; i < items.size(); i++) {
                    brd = (Bird) items.get(i);
                    if (brd.getName().getId() == name.getId()) {
                        items.remove(i);
//                    return brd;
                        break;
                    }
//                return brd;
                }
            return brd;
        }

        public int getBirdBalance(Name name) {
            int balance = 0;

            for (Object bird : items) {
                if (((Bird) bird).getName().getId() == name.getId()) {
                    balance++;
                }

            }
//            for (int i = 0; i < items.size(); i++){
//                if (((Bird)items.get(i)).getName().getId() == name.getId()){
//                    balance ++;
//                }
//            }
            return balance;
        }
    }

    public Storages() {
        birdStorage = new BirdStorage();
        customerStorage = new CustomerStorage();
        prchsStrg = new PurchaseStorage();
    }


    // vynimanie pticy iz hranilischa
    public Bird getBird(Name name) {

//        Bird brd = null;
//        if (brdStrg[name.getId()][0] == null) {
//            System.out.println("Sorry, we have no this birds anymore =(");// insert Exception here,
//        }
//        for (int i = 0; i < 100; i++) {
//            if (brdStrg[name.getId()][i] == null) {
//                brd = brdStrg[name.getId()][i - 1];
//                brdStrg[name.getId()][i - 1] = null;
//                break;
//            }
//        }
        return birdStorage.getBird(name);
    }

    //rabotaem s pokupatelem, zdes' est vozmognost' tol'ko dobavleniya
    public void insertPurchase(Purchase prchs) {
        prchsStrg.add(prchs);
    }

    public List<Purchase> purchaseInStorage() {

        return prchsStrg.getItems();
    }

    public int getBirdBalance(Name name) {
        return birdStorage.getBirdBalance(name);
    }

//    public Bird birdInStorage(Name name, int number) {
//        final Bird brd = birdStorage.[name.getId()][number]; // is it correct?
//        return brd;
//    }

    // rabotaem s pticami (tovar)
    // dibavlenie novoi pticy v hranilische
    public void insertBird(Bird bird) {

        birdStorage.add(bird);
    }


    class SimpleStorage<T> {
        List<T> items = new ArrayList<>();

        public void SimpleStorage() {

        }

        public void add(T item) {       //adding an item
            items.add(item);
        }

        public List<T> getItems() {  //list of all items
            return new ArrayList<>(items);
        }

        public T get(int index) {
            return items.get(index);
        }
    }
}
