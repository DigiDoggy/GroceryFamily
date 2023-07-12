package com.example.FinalProject.Parser;

import com.example.FinalProject.model.Product;
import com.example.FinalProject.service.GroceryInfoService;
import com.example.FinalProject.service.GroceryService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
abstract class WebParser {

    protected String url;
    protected WebDriver driver;
    @Autowired
    protected GroceryInfoService groceryInfoService;

    WebParser(WebDriver driver,GroceryInfoService groceryInfoService){
        this.driver=driver;
        this.groceryInfoService=groceryInfoService;
    }
    //Setters/Getters

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public WebDriver getDriver() {
        return driver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }

    //Methods
    void scrapeWebSite() {
        logIntoWebSite(getUrl());
        removeCookiePopup();
    }

    void logIntoWebSite(String url) {
        driver.get(url);
    }

    void removeCookiePopup() {
    }

    void searchProduct(String pageSelector) {
       List<String> productName= groceryInfoService.getProductName();
        for (String name: productName) {
            WebElement searchingBar= driver.findElement(By.id(pageSelector));
            searchingBar.sendKeys(name);
            //need method from checkingName class

            searchingBar.click();

        }

    }

    void addToCard() {
    }


}
