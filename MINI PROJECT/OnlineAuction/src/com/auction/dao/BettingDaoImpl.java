package com.auction.dao;



import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.auction.model.Betting;
import com.auction.util.DBHandler;

public class BettingDaoImpl implements BettingDao {

    @Override
    public void create(Betting betting) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHandler.getConnection();
            String query = "INSERT INTO Betting (Betting_product, Betting_amount, Bidder_name, customerid) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(query);
            ps.setString(1, betting.getBettingProduct());
            ps.setLong(2, betting.getBettingAmount());
            ps.setString(3, betting.getBidderName());
            ps.setInt(4, betting.getCustomerId());
            ps.executeUpdate();
            System.out.println("Bidding created successfully.");
        } catch (SQLException e) {
            System.out.println("Error while creating bidding: " + e.getMessage());
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
    public Betting getHighestBidForProduct(String product) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        Betting betting = null;
        try {
            conn = DBHandler.getConnection();
            String query = "SELECT * FROM Betting WHERE Betting_product=? ORDER BY Betting_amount DESC LIMIT 1";
            ps = conn.prepareStatement(query);
            ps.setString(1, product);
            rs = ps.executeQuery();
            if (rs.next()) {
                betting = new Betting();
                betting.setBettingId(rs.getInt("Betting_id"));
                betting.setBettingProduct(rs.getString("Betting_product"));
                betting.setBettingAmount(rs.getLong("Betting_amount"));
                betting.setBidderName(rs.getString("Bidder_name"));
                betting.setCustomerId(rs.getInt("customerid"));
            }
        } catch (SQLException e) {
            System.out.println("Error while fetching highest bid: " + e.getMessage());
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
        return betting;
    }

    @Override
    public void updateHighestBid(Betting betting) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            conn = DBHandler.getConnection();
            String query = "UPDATE Betting SET Betting_amount=?, Bidder_name=?, customerid=? WHERE Betting_id=?";
            ps = conn.prepareStatement(query);
            ps.setLong(1, betting.getBettingAmount());
            ps.setString(2, betting.getBidderName());
            ps.setInt(3, betting.getCustomerId());
            ps.setInt(4, betting.getBettingId());
            ps.executeUpdate();
            System.out.println("Bidding updated successfully.");
        } catch (SQLException e) {
            System.out.println("Error while updating bidding: " + e.getMessage());
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
