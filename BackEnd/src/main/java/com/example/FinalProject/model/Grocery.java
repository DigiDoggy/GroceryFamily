package com.example.FinalProject.model;


import javax.persistence.*;
import java.io.Serializable;
import java.util.UUID;

@Entity
public class Grocery implements Serializable {

    @Id
    @GeneratedValue
    @Column(nullable = false,updatable = false, name = "grocery_code", columnDefinition = "BINARY(16)")
    private UUID groceryCode;
    private String name;
    private String quantity;
    private String rimiPrice;
    private String prismaPrice;
    private String barboraPrice;




 public Grocery(){
 }

    public Grocery( String name, String quantity, String rimiPrice, String prismaPrice, String barboraPrice, UUID groceryCode) {

        this.name = name;
        this.quantity = quantity;
        this.rimiPrice = rimiPrice;
        this.prismaPrice = prismaPrice;
        this.barboraPrice = barboraPrice;
        this.groceryCode=groceryCode;
    }


    public UUID getGroceryCode() {
        return groceryCode;
    }

    public void setGroceryCode(UUID groceryCode) {
        this.groceryCode = groceryCode;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getRimiPrice() {
        return rimiPrice;
    }

    public void setRimiPrice(String rimiPrice) {
        this.rimiPrice = rimiPrice;
    }

    public String getPrismaPrice() {
        return prismaPrice;
    }

    public void setPrismaPrice(String prismaPrice) {
        this.prismaPrice = prismaPrice;
    }

    public String getBarboraPrice() {
        return barboraPrice;
    }

    public void setBarboraPrice(String barboraPrice) {
        this.barboraPrice = barboraPrice;
    }



    @Override
    public String toString() {
        return "Grocery{" +
                ", name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", rimiPrice='" + rimiPrice + '\'' +
                ", prismaPrice='" + prismaPrice + '\'' +
                ", barboraPrice='" + barboraPrice + '\'' +
                ", groceryCode='" + groceryCode + '\'' +
                '}';
    }
}
