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
    private String coopPrice;
    private String barboraPrice;


 public Grocery(){
 }

    public Grocery(Long id, String name, String quantity, String rimiPrice, String coopPrice, String barboraPrice) {
        this.id = id;
        this.name = name;
        this.quantity = quantity;
        this.rimiPrice = rimiPrice;
        this.coopPrice = coopPrice;
        this.barboraPrice = barboraPrice;
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

    public String getCoopPrice() {
        return coopPrice;
    }

    public void setCoopPrice(String coopPrice) {
        this.coopPrice = coopPrice;
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
                ", coopPrice='" + coopPrice + '\'' +
                ", barboraPrice='" + barboraPrice + '\'' +
                '}';
    }
}
