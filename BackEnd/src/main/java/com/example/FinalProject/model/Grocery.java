package com.example.FinalProject.model;


import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Grocery implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false,updatable = false)
    private Long id;
    private String name;
    private String quantity;
    private String rimiPrice;
    private String prismaPrice;
    private String barboraPrice;

    @Column(nullable = false,updatable = false)
    private String groceryCode;


 public Grocery(){
 }

    public Grocery(Long id, String name, String quantity, String rimiPrice, String prismaPrice, String barboraPrice, String groceryCode) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.rimiPrice = rimiPrice;
        this.prismaPrice = prismaPrice;
        this.barboraPrice = barboraPrice;
        this.groceryCode=groceryCode;
    }


    public String getGroceryCode() {
        return groceryCode;
    }

    public void setGroceryCode(String groceryCode) {
        this.groceryCode = groceryCode;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
                "id=" + id +
                ", name='" + name + '\'' +
                ", quantity='" + quantity + '\'' +
                ", rimiPrice='" + rimiPrice + '\'' +
                ", prismaPrice='" + prismaPrice + '\'' +
                ", barboraPrice='" + barboraPrice + '\'' +
                ", groceryCode='" + groceryCode + '\'' +
                '}';
    }
}
