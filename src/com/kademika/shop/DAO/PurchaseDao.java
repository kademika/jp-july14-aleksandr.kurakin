package com.kademika.shop.DAO;

import com.kademika.shop.ConnectionFactory;
import com.kademika.shop.entitys.Purchase;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kurakinaleksandr on 01.03.15.
 */
public class PurchaseDao {
    private Connection connection;

    public PurchaseDao() {
//        ConnectionFactory cf = new ConnectionFactory();
        connection = ConnectionFactory.getConnection();
    }

    public void insertPurchase(Purchase purchase) {
        int quantity = purchase.getNumberOfBirds();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        String timeOfPurchase = sdf.format(new java.util.Date());
        int customerID = 0;
        int birdId = 0;

        String birdIDQuery = "select * from bird_storage where type_name = '" + purchase.getName().toString() + "';";
        String customerIDQuery = "select * from customers where name = '" + purchase.getCustomer() + "';";

        try {

            Statement statement = connection.createStatement(); // into method
            ResultSet rsBirdID = statement.executeQuery(birdIDQuery);
            if (rsBirdID.next()) {
                birdId = rsBirdID.getInt(1);
            }
            System.out.println(birdId);

            ResultSet rsCustomerID = statement.executeQuery(customerIDQuery);
            if (rsCustomerID.next()) {
                customerID = rsCustomerID.getInt(1);
            }
            String prchsUpdate = "insert into purchases (customerID, birdID, quantity, purchase_date) values (" + customerID + ", " + birdId + ", " +
                    quantity + ", '" + timeOfPurchase + "');";

            statement.execute(prchsUpdate);

            String birdsUpdate = "update bird_storage set quantity = quantity - " + quantity + " where type_name = '" + purchase.getName().toString()
                    + "';";

            statement.execute(birdsUpdate);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Purchase> getAllPurchases() {
        List<Purchase> result = new ArrayList<>();

        return result;
    }
}
