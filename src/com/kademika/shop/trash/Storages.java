package com.kademika.shop.trash;

import com.kademika.shop.entitys.Customer;
import com.kademika.shop.entitys.Purchase;
import com.kademika.shop.constants.Name;

import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class Storages {

//    private Statement statement;
    private Connection connection;

    public Storages() {
//        try {
////      Class.forName("com.mysql.jdbc.Driver");
//            String URL = "jdbc:mysql://localhost:3306/My_first_DB";
//            String USER = "root";
//            String PASS = "";
//            Connection connection = DriverManager.getConnection(URL, USER, PASS);
//            System.out.println("Connected.");
////            statement = connection.createStatement(); // into method
//            System.out.println("Statement created."); // into method
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//        catch (ClassNotFoundException e) {
//            e.printStackTrace();
//        }

        // KILL

//        birdStorage = new BirdStorage();
//        customerStorage = new CustomerStorage();
//        prchsStrg = new PurchaseStorage();
    }

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        String getCustomersQuery = "select * from customers;";
        try{
            Statement statement = connection.createStatement(); // into method
            ResultSet rsGetCustomers = statement.executeQuery(getCustomersQuery);
            Customer tmpCustomer = new Customer();
            while (rsGetCustomers.next()){
//                Customer tmpCustomer = new Customer();
                tmpCustomer.setName(rsGetCustomers.getString("name"));
                customers.add(tmpCustomer);
            }
        }catch (SQLException e){
            e.printStackTrace();

        } finally {

        }
        return customers;

//        return customerStorage.getItems();
    }

    public void addCustomer(Customer customer) {
        String customerName = customer.getName();
//        String checkCustomerInStorage = "select * from customers where name = '" + customerName + "';";

        String insertCustomerQuery = "insert into customers (name) values (?);";
        try {
            PreparedStatement ps = connection.prepareStatement(insertCustomerQuery); // into method
            ps.setString(1, customerName);
            ps.execute();
//            ResultSet rsCheckCustomerInStorage = statement.executeQuery(checkCustomerInStorage);
//            if (!rsCheckCustomerInStorage.next()) {
//                statement.execute(insertCustomerQuery);
//            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getSQLState());
        } finally {
//            JdbcUtils.
        }
//        customerStorage.add(customer);
    }

    public int getBirdBalance(Name name) {
        int birdQuantity = 0;
        String birdBalanceQuery = "select * from bird_storage where type_name = '" + name.toString() + "';";
        try {
            Statement statement = connection.createStatement(); // into method
            ResultSet rsBirdQuantity = statement.executeQuery(birdBalanceQuery);
            if (rsBirdQuantity.next()) {
                birdQuantity = rsBirdQuantity.getInt("quantity");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return birdQuantity;

//        return birdStorage.getBirdBalance(name);
    }

    public void addPurchase(Purchase prchs) {

//        Statement statement = dbConnection.getStatement();

        int quantity = prchs.getNumberOfBirds();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String timeOfPurchase = sdf.format(new java.util.Date());
        int customerID = 0;
        int birdId = 0;

        String birdIDQuery = "select * from bird_storage where type_name = '" + prchs.getName().toString() + "';";
        String customerIDQuery = "select * from customers where name = '" + prchs.getCustomer() + "';";

        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            Connection con = DriverManager.getConnection(URL, USER, PASS);
//            System.out.println("Connected.");
//            statement = con.createStatement();
//            System.out.println("Statement created.");
            Statement statement = connection.createStatement(); // into method
            ResultSet rsBirdID = statement.executeQuery(birdIDQuery);
            if (rsBirdID.next()) {
                birdId = rsBirdID.getInt("birdID");
            }
            System.out.println(birdId);

            ResultSet rsCustomerID = statement.executeQuery(customerIDQuery);
            if (rsCustomerID.next()) {
                customerID = rsCustomerID.getInt("customersID");
            }
            String prchsUpdate = "insert into purchases (customerID, birdID, quantity, purchase_date) values (" + customerID + ", " + birdId + ", " +
                    quantity + ", '" + timeOfPurchase + "');";
//            System.out.println(prchsUpdate);

            statement.execute(prchsUpdate);

            String birdsUpdate = "update bird_storage set quantity = quantity - " + quantity + " where type_name = '" + prchs.getName().toString()
                    + "';";
//            System.out.println(birdsUpdate);

            statement.execute(birdsUpdate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

