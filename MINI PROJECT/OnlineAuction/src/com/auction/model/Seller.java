package com.auction.model;

public class Seller {
    private int sellerId;
    private String sellerProduct;
    private String sellerName;
    private int customerId;

    // Getters and Setters

    public int getSellerId() {
        return sellerId;
    }

    public void setSellerId(int sellerId) {
        this.sellerId = sellerId;
    }

    public String getSellerProduct() {
        return sellerProduct;
    }

    public void setSellerProduct(String sellerProduct) {
        this.sellerProduct = sellerProduct;
    }

    public String getSellerName() {
        return sellerName;
    }

    public void setSellerName(String sellerName) {
        this.sellerName = sellerName;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Seller [sellerId=" + sellerId + ", sellerProduct=" + sellerProduct + ", sellerName=" + sellerName
                + ", customerId=" + customerId + "]";
    }
}
