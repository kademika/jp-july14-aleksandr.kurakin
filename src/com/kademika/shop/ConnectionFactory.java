package com.kademika.shop;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by kurakinaleksandr on 01.03.15.
 */
public class ConnectionFactory {
    private static String DRIVER;
    private static String URL;
    private static String USER;
    private static String PASS;

    public ConnectionFactory() {

    }

    public static Connection getConnection() {
        DRIVER = "com.mysql.jdbc.Driver";
        URL = "jdbc:mysql://localhost:3306/My_first_DB";
        USER = "root";
        PASS = "";
        Connection connection = null;
        try {
//            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASS);
            connection.getTransactionIsolation();
            System.out.println("Connected.");
        } catch (SQLException  e) {
            e.printStackTrace();
        }
       return connection;
    }

}
