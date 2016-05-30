package com.kademika.shop;

import com.mysql.cj.jdbc.Driver;

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
        DRIVER = "com.mysql.cj.jdbc.Driver";
        URL = "jdbc:mysql://localhost:3306/My_first_DB?verifyServerCertificate=false&useSSL=false&requireSSL=false&serverTimezone=UTC&useTimezone=true";
        USER = "root";
        PASS = "";
        Connection connection = null;
        try {
            Driver driver = new Driver();
            DriverManager.registerDriver(driver);
//            Class.forName(DRIVER);
            connection = DriverManager.getConnection(URL, USER, PASS);

            connection.getTransactionIsolation();
            System.out.println("Connected.");
        } catch (SQLException  e) {
            e.printStackTrace();
        }
//        catch (ClassNotFoundException e){
//            System.out.println("Can not connect driver");
//        }
        return connection;
    }

}
