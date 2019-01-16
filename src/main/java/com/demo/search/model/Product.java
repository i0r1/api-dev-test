package com.demo.search.model;

public class Product {
    private String productName;
    private float bestPrice;
    private String currency;
    private String location;

    public Product(String productName, float bestPrice, String currency, String location) {
        this.productName = productName;
        this.bestPrice = bestPrice;
        this.currency = currency;
        this.location = location;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getBestPrice() {
        return bestPrice;
    }

    public void setBestPrice(float bestPrice) {
        this.bestPrice = bestPrice;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
