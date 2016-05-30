package com.kademika.shop.DAO;

import com.kademika.shop.ConnectionFactory;
import com.kademika.shop.constants.Name;
import com.kademika.shop.entitys.Purchase;

import java.util.*;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by kurakinaleksandr on 01.03.15.
 */
public class BirdDao {
    private Connection connection;

    public BirdDao() {
        connection = ConnectionFactory.getConnection();
    }

    public int getBirdBalance(Name name) {
        int birdQuantity = 0;
        String birdBalanceQuery = "select * from bird_storage where type_name = '" + name.toString() + "';";
        try {
            Statement statement = connection.createStatement(); // into method
            ResultSet rsBirdQuantity = statement.executeQuery(birdBalanceQuery);
            if (rsBirdQuantity.next()) {
                birdQuantity = rsBirdQuantity.getInt(4);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return birdQuantity;
    }

    public void birdStrgUpdate(Purchase purchase) {
        int quantity = purchase.getNumberOfBirds();
        String birdName = purchase.getName().toString();
        String birdsUpdate = "update bird_storage set quantity = quantity - " + quantity + " where type_name = '" + birdName + "';";
        try {
            Statement statement = connection.createStatement();
            statement.execute(birdsUpdate);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<String> getCatalog() {
        List<String> catalog = new ArrayList<>();
        String birdBalanceQuery = "select type_name from bird_storage";
        try {
            Statement statement = connection.createStatement(); // into method
            ResultSet rsBirdQuantity = statement.executeQuery(birdBalanceQuery);
            while (rsBirdQuantity.next()) {
                catalog.add(rsBirdQuantity.getString(1));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
// need to close statement?
        }

        return catalog;
    }
}
