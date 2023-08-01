package com.auction.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.auction.model.Customer;
import com.auction.util.DBHandler;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public void create(Customer customer) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHandler.getConnection();
            String query = "INSERT INTO customer (customer_name, customer_email, password, phone) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, customer.getCustomerName());
            ps.setString(2, customer.getCustomerEmail());
            ps.setString(3, customer.getPassword());
            ps.setLong(4, customer.getPhone());
            ps.executeUpdate();
            System.out.println("Customer created successfully.");
        } catch (SQLException e) {
            System.out.println("Error while creating customer: " + e.getMessage());
        } finally {
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error while closing resources: " + e.getMessage());
            }
        }
    }

    @Override
    public Customer getCustomerByEmail(String email) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Customer customer = null;
        try {
            conn = DBHandler.getConnection();
            String query = "SELECT * FROM customer WHERE customer_email=?";
            ps = conn.prepareStatement(query);
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                customer = new Customer();
                customer.setCustomerId(rs.getInt("customerid"));
                customer.setCustomerName(rs.getString("customer_name"));
                customer.setCustomerEmail(rs.getString("customer_email"));
                customer.setPassword(rs.getString("password"));
                customer.setPhone(rs.getLong("phone"));
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching customer: " + e.getMessage());
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error while closing resources: " + e.getMessage());
            }
        }
        return customer;
    }
}
