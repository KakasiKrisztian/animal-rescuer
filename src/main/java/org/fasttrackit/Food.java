package org.fasttrackit;

public class Food {
    String name;
    double price;
    double quantity;
    boolean inStock;
    java.time.LocalDateTime expiryDate;

    //optional
    String manufacturer;
    boolean bioFood;
    boolean allergyProducingFood;
    String foodType;

    public Food(String name, double price, double quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}