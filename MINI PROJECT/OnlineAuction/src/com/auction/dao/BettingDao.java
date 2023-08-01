package com.auction.dao;

import com.auction.model.Betting;

public interface BettingDao {
    void create(Betting betting);
    Betting getHighestBidForProduct(String product);
    void updateHighestBid(Betting betting);
}
