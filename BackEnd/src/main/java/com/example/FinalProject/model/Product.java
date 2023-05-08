package com.example.FinalProject.model;

import java.math.BigDecimal;

public class Product {

    // path to more product //*[@id="main"]/section/div[1]/div/div[2]/div[1]/div/div[2]/ul/li[1]/div/div[3]/div/form[3]/button[2]/svg/path
    private String name;

    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    private BigDecimal pricePerUnit;
    private Measurement measurement;
    //todo mb need create something for bottles (Pant price is 0.10 eur)
    private BigDecimal price;
    private String pathToProductTag;
    // For add product to card
    //  only for rimi yet
    private String addToCard="Lisa ostukorvi";



    public Product(String name, BigDecimal pricePerUnit, BigDecimal price) {
        this.name = name;
        this.pricePerUnit = pricePerUnit;
        this.price = price;
        this.measurement = Measurement.setValueUnit(Measurement.getArrayFromProductName(name));
    }




    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + pricePerUnit +
                ", measurement=" + measurement +
                '}';
    }
//Get/Setters

    public String getPathToProductTag() {
        return pathToProductTag;
    }

    public void setPathToProductTag(int i) {
        this.pathToProductTag = "//*[@id='main']/section/div[1]/div/div[2]/div[1]/div/div[2]/ul/li["+ i +"]/div/div[3]/div/form[2]/button";
    }

    public String getAddToCardPage() {
        return addToCard;
    }


    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return pricePerUnit;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

}
