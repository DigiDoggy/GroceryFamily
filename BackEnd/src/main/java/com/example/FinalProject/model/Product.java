package com.example.FinalProject.model;

import java.math.BigDecimal;

public class Product {
    private String name;
    private BigDecimal pricePerUnit;
    private Measurement measurement;
    private BigDecimal price;
    private String addToCardPage;



    public Product(String name, BigDecimal pricePerUnit, BigDecimal price,String addToCardPage) {
        this.name = name;
        this.pricePerUnit = pricePerUnit;
        this.price = price;
        this.addToCardPage=addToCardPage;
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

    public String getAddToCardPage() {
        return addToCardPage;
    }

    public void setAddToCardPage(String addToCardPage) {
        this.addToCardPage = addToCardPage;
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
