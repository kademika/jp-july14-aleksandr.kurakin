package com.kademika.shop;

import java.beans.*;
import java.sql.*;
import java.sql.Statement;

/**
 * Created by kurakinaleksandr on 15.02.15.
 */
public class DBConnection {
    private static String URL = "jdbc:mysql://localhost:3306/My_first_DB";
    private static String USER = "root";
    private static String PASS = "";
    public static Statement statement;

    public void DBConnection(){
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection con = DriverManager.getConnection(URL, USER, PASS);
            System.out.println("Connected.");
            statement = con.createStatement();
            System.out.println("Statement created.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public Statement getStatement(){
        return statement;
    }
}
