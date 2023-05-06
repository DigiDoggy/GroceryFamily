package com.example.FinalProject.model;

import java.math.BigDecimal;

public class Product {
    private String name;
    private BigDecimal price;
    private Measurement measurement;

    public Product(String name, BigDecimal price) {
        this.name = name;
        this.price = price;
        this.measurement = Measurement.setValueUnit(Measurement.getArrayFromProductName(name));
    }


    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", price=" + price +
                ", measurement=" + measurement +
                '}';
    }

    public String getName() {
        return name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public Measurement getMeasurement() {
        return measurement;
    }

}
