package com.auction.dao;

import com.auction.model.Customer;

public interface CustomerDao {
    void create(Customer customer);
    Customer getCustomerByEmail(String email);
}
