package com.kademika.shop.DAO;

import com.kademika.shop.ConnectionFactory;
import com.kademika.shop.entitys.Customer;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by kurakinaleksandr on 01.03.15.
 */
public class CustomerDao {
    private Connection connection;

    public CustomerDao() {
        connection = ConnectionFactory.getConnection();
    }

    public void insertCustomer(Customer customer) {
        String insertCustomerQuery = "insert into customers (name) values (?)";
        try {
            PreparedStatement ps = connection.prepareStatement(insertCustomerQuery);
            ps.setString(1, customer.getName());
            ps.execute();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println(e.getSQLState());
        } finally {
// do we need to close statement?
        }

    }

    public List<Customer> getAllCustomers() {
        List<Customer> customers = new ArrayList<>();
        String getCustomersQuery = "select name from customers";
        try {
            Statement statement = connection.createStatement();
            ResultSet rsGetCustomers = statement.executeQuery(getCustomersQuery);
            while (rsGetCustomers.next()) {
                Customer customer = new Customer();
                customer.setName(rsGetCustomers.getString(1));
                customers.add(customer);
            }
        } catch (SQLException e) {
            e.printStackTrace();

        } finally {
// need to close statement?
        }
        return customers;
    }

    public int getCustomerId(Customer customer) {
        int customerId = 0;
        String getCustomerIdQuery = "select * from customers where name = ?";
        try {
            PreparedStatement ps = connection.prepareStatement(getCustomerIdQuery);
            ps.setString(1, customer.getName());
            ResultSet rs = ps.executeQuery();
            customerId = rs.getInt(1);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return customerId;
    }
}
