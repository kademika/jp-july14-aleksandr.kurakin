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

    public Storages() {
        birdStorage = new BirdStorage();
        customerStorage = new CustomerStorage();
        prchsStrg = new PurchaseStorage();
    }

    public List<Customer> getCustomers() {
        return customerStorage.getItems();
    }

    public void insertCustomer(Customer customer) {
        customerStorage.add(customer);
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

    public int getBirdBalance(Name name) {
        return birdStorage.getBirdBalance(name);
    }

    //rabotaem s pokupatelem, zdes' est vozmognost' tol'ko dobavleniya, povtorno pokupateli ne dobavliautsa - zamenit' na druguu collection
    public void insertPurchase(Purchase prchs) {
        //проверка наличия такого же покупателя - если нет - добавляем

        prchsStrg.add(prchs);
    }

    // получаем список покупателей
    public List<Customer> getAllCustomers() {

        return customerStorage.getItems();
    }

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

    class PurchaseStorage<T extends Purchase> extends SimpleStorage {

    }

    class CustomerStorage<T extends Customer> extends SimpleStorage {
        @Override
        public void add(Object item) {
            int flag = 0;
            for (Object customer : items) {
                if (item.equals(customer)) {
                    flag++;
                }
            }
            if (flag == 0) {
                super.add(item);
            }
        }
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

}

