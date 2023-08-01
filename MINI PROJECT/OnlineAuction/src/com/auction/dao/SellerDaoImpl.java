package com.auction.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.auction.model.Seller;
import com.auction.util.DBHandler;

public class SellerDaoImpl implements SellerDao {

    @Override
    public void create(Seller seller) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHandler.getConnection();
            String query = "INSERT INTO Seller (Seller_product, Seller_name, customerid) VALUES (?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, seller.getSellerProduct());
            ps.setString(2, seller.getSellerName());
            ps.setInt(3, seller.getCustomerId());
            ps.executeUpdate();
            System.out.println("Seller record created successfully.");
        } catch (SQLException e) {
            System.out.println("Error while creating seller record: " + e.getMessage());
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
}
