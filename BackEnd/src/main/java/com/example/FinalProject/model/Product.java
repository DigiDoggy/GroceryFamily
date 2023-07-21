package com.example.FinalProject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Product {

    // path to more product //*[@id="main"]/section/div[1]/div/div[2]/div[1]/div/div[2]/ul/li[1]/div/div[3]/div/form[3]/button[2]/svg/path
    @JsonProperty("title")//for barbora
    private String name;
    @JsonProperty("price")//barbora
    private BigDecimal price;

    private BigDecimal pricePerUnit;

    private Measurement measurement;
    //mb good plan to not create 2 path, and just create which number of li that product is.
    private String pathToProductTag;
    private String pathToAddMore;
    // For add product to card
    //  only for rimi yet
    private String addToCard = "Lisa ostukorvi";

    @JsonIgnore
    public BigDecimal getPricePerUnit() {
        return pricePerUnit;
    }

    public void setPricePerUnit(BigDecimal pricePerUnit) {
        this.pricePerUnit = pricePerUnit;
    }


    public Product() {
    }

    ;

    public Product(String name, BigDecimal pricePerUnit, BigDecimal price) {
        this.name = name;
        this.pricePerUnit = pricePerUnit;
        this.price = price;
        this.measurement = Measurement.setValueUnit(name);
    }


    @Override
    public String toString() {
        return "Product{" +
                "name='" + name + '\'' +
                ", pricePerUnit=" + pricePerUnit +
                ", measurement=" + measurement +
                ", price=" + price +


                '}';
    }
//Get/Setters


    public void setName(String name) {
        this.name = name.toLowerCase();
    }

    public String getPathToAddMore() {
        return pathToAddMore;
    }

    public void setPathToAddMore(String pathToAddMore) {
        this.pathToAddMore = pathToAddMore;
    }

    public String getPathToProductTag() {
        return pathToProductTag;
    }

    public void setPathToProductTag(int i) {
        this.pathToProductTag = "//*[@id='main']/section/div[1]/div/div[2]/div[1]/div/div[2]/ul/li[" + i + "]/div/div[3]/div/form[2]/button";
        setPathToAddMore("//*[@id=\"main\"]/section/div[1]/div/div[2]/div[1]/div/div[2]/ul/li[" + i + "]/div/div[3]/div/form[3]/button[2]");
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

    public void setMeasurement(String productName) {
        this.measurement = Measurement.setValueUnit(productName);
    }
}
