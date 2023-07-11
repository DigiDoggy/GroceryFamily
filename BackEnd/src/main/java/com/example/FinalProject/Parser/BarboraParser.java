package com.example.FinalProject.Parser;

import com.example.FinalProject.model.Product;

import java.util.List;

public class BarboraParser implements WebSiteParser{
    private String barboraUrl="barbora.ee";
    public List<Product> parserProducts() {
        return null;
    }

    @Override
    public void scrapeWebSite() {

    }

    @Override
    public void logIntoWebSite() {

    }

    @Override
    public void removeCookiePopup() {

    }

    @Override
    public void searchProduct() {

    }

    @Override
    public void addToCard() {

    }
    // Getters and Setters

    public String getBarboraUrl() {
        return barboraUrl;
    }


}
