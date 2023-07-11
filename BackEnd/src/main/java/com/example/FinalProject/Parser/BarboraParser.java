package com.example.FinalProject.Parser;

import com.example.FinalProject.model.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BarboraParser implements WebSiteParser{

    public String productName;
    private WebDriver driver;
    private String barboraUrl="http://www.barbora.ee";
    public List<Product> parserProducts() {
        return null;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BarboraParser(WebDriver driver){
        this.driver=driver;
    }



    @Override
    public void scrapeWebSite() {
        logIntoWebSite(barboraUrl);
        removeCookiePopup();
        searchProduct();
    }

    @Override
    public void logIntoWebSite(String BarboraUrl) {
        driver.get(BarboraUrl);
    }

    @Override
    public void removeCookiePopup() {
     WebElement element = driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll"));
     element.click();
    }

    @Override
    public void searchProduct() {
        WebElement searchInput = driver.findElement(By.id("fti-search"));
        searchInput.sendKeys(productName);
        searchInput.submit();
    }

    @Override
    public void addToCard() {

    }
    // Getters and Setters

    public String getBarboraUrl() {
        return barboraUrl;
    }


}
