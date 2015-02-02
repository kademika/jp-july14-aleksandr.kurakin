package com.kademika.day14;

import java.sql.*;

/**
 * Created by kurakinaleksandr on 02.02.15.
 */
public class CreatingConnection {

    public static void main(String[] args) {
//        Connection con = null;
        Statement statement = null;

        try {
            Class.forName("com.mysql.jdbc.Driver");// загружаем драйвер
            System.out.println("Driver loading success!");
            //у MySQL обязательно есть системная база,
            //к ней и будем создавать соединение.
            String url = "jdbc:mysql://localhost:3306/My_first_DB";
            String name = "root";
            String password = "";
            try {
                Connection con = DriverManager.getConnection(url, name, password);
                System.out.println("Connected.");
                statement = con.createStatement();
                String sqlCommand = "select * from birds";

                ResultSet rs = statement.executeQuery(sqlCommand);
                while(rs.next()){
                    int birdID = rs.getInt("idbirds");
                    String birdType = rs.getString("type_name");
                    Double price = rs.getDouble("price");
                    System.out.println(" " + birdID + " " + birdType + " " + price + " End of line");
                }

                Thread.sleep(10000);
                con.close();
                System.out.println("Disconnected.");
            } catch (SQLException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
