package com.auction.model;

public class Betting extends Customer{
    private int bettingId;
    private String bettingProduct;
    private long bettingAmount;
    private String bidderName;
    private int customerId;

    // Getters and Setters

    public int getBettingId() {
        return bettingId;
    }

    public void setBettingId(int bettingId) {
        this.bettingId = bettingId;
    }

    public String getBettingProduct() {
        return bettingProduct;
    }

    public void setBettingProduct(String bettingProduct) {
        this.bettingProduct = bettingProduct;
    }

    public long getBettingAmount() {
        return bettingAmount;
    }

    public void setBettingAmount(long bettingAmount) {
        this.bettingAmount = bettingAmount;
    }

    public String getBidderName() {
        return bidderName;
    }

    public void setBidderName(String bidderName) {
        this.bidderName = bidderName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Betting [bettingId=" + bettingId + ", bettingProduct=" + bettingProduct + ", bettingAmount="
                + bettingAmount + ", bidderName=" + bidderName + ", customerId=" + customerId + "]";
    }
}