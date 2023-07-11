package com.example.FinalProject.Parser;

import com.example.FinalProject.model.Product;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class RimiParser implements WebSiteParser{
    private String rimiUrl = "rimi.ee";
    private WebDriver driver;

    public List<Product> parserProducts() {
        return null;
    }

    @Override
    public void scrapeWebSite() {
        driver.get(rimiUrl);



    }

    @Override
    public void logIntoWebSite(String rimiUrl) {

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
}
