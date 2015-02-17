package com.kademika.shop;

import com.kademika.shop.constants.Name;
import com.kademika.shop.Bird;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

// Storages hranit infomatciu o tovarah, pokupatel'ah i pokupkah, eto interface k BD
public class Storages {

//    private DBConnection dbConnection;
    private CustomerStorage customerStorage;
    private PurchaseStorage prchsStrg;
    private BirdStorage birdStorage;
    private static String URL = "jdbc:mysql://localhost:3306/My_first_DB";
    private static String USER = "root";
    private static String PASS = "";
    public static Statement statement;

    public Storages() {
//        dbConnection = new DBConnection();
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

    //rabotaem s pokupkoy, zdes' est vozmognost' tol'ko dobavleniya, povtorno pokupateli ne dobavliautsa - zamenit' na druguu collection
    public void insertPurchase(Purchase prchs) {

//        Statement statement = dbConnection.getStatement();

        int quantity = prchs.getNumberOfBirds();
//        Date timeOfPurchase = prchs.getPurchaseTime();
        int customerID = 0;
        int birdId = 0;

        String birdIDQuery = "select * from bird_storage where type_name = '" + prchs.getName().toString() + "';";
        String customerIDQuery = "select * from customers where name = '" + prchs.getCustomer() + "';";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Connected.");
            statement = con.createStatement();
            System.out.println("Statement created.");
            ResultSet rsBirdID = statement.executeQuery(birdIDQuery);
            if (rsBirdID.next()) {
                birdId = rsBirdID.getInt("birdID");
            }
            System.out.println(birdId);
            ResultSet rsCustomerID = statement.executeQuery(customerIDQuery);
            if(rsCustomerID.next()) {
                customerID = rsCustomerID.getInt("customersID");
            }
            String prchsUpdate = "insert into purchases (customerID, birdID, quantity) values (" + customerID + ", " + birdId + ", " +
                    quantity +  ");";

            statement.execute(prchsUpdate);

            String birdsUpdate = "update bird_storage set quantity = quantity - " + quantity + " where type_name = '" + prchs.getName().toString() + "';";

            statement.execute(birdsUpdate);
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        }

//        prchsStrg.add(prchs);
    }

    // получаем список покупателей
    public List<Customer> getAllCustomers() {

        return customerStorage.getItems();
    }

    // rabotaem s pticami (tovar)
    // dibavlenie novoi pticy v hranilische
    public void insertBird(Bird bird) {
//        Statement statement = dbConnection.getStatement();
//        String type_name = bird.getName().toString();
//        double price = bird.getPrice();
//        int quantity = 1;
//        try {
//            statement.execute("insert ");
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//

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

