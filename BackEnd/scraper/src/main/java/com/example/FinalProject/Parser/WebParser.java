package com.example.FinalProject.Parser;

import com.example.FinalProject.model.Product;
import com.example.FinalProject.service.GroceryInfoService;
import com.example.FinalProject.similarity.ProductFilter;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

@Component
abstract class WebParser {

    protected String url;
    protected String addToCardLinktext;
    protected WebDriver driver;
    protected List<Product> onThePage;
    protected List<String> namesFromDB;
    protected List<String> quantityFromDB;


    @Autowired
    protected GroceryInfoService groceryInfoService;

    WebParser(WebDriver driver, GroceryInfoService groceryInfoService) {
        this.driver = driver;
        this.groceryInfoService = groceryInfoService;
    }
    //Setters/Getters


    public List<String> getNamesFromDB() {
        return namesFromDB = groceryInfoService.getProductName();
    }

    public List<String> getQuantityFromDB() {
        return quantityFromDB = groceryInfoService.getQuantity();
    }

    public List<Product> getOnThePage() {
        return onThePage;
    }

    public void setOnThePage(List<Product> onThePage) {
        this.onThePage = onThePage;
    }

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
    }

    void logIntoWebSite(String url) {
        driver.get(url);
    }

    void searchGrocery(List<String> namesFromDB) {

    }

    //for work for Rimi and Barbora it is good.
    // For Prisma I don't need consent to save cookie
    void removeCookiePopup() {
        WebElement element =
                driver.findElement(By
                        .id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll"));


        element.click();
    }


    // Filter
    public Product getCheapestProduct(List<Product> products) {
        return ProductFilter.cheaperPrice(products);

    }

    // изменить метот в фильтре чтоб работал с арей листами
    public List<Product> nameFilter(List<Product> products, String nameFromDB) {
        ProductFilter.containsAllWords(products, nameFromDB);

        for (Product product : products)
            if (product != null) {
                System.out.println("nameFilter" + product);
            } else {
                System.out.println("nameFilter don`t work");
            }

        return products;
    }

    //get List of product on the page. Parsing for getting all information
    // about product on the searching page
    abstract public List<String> getGroceriesInfoOnThePage(String cssSelector);

    //Searching all price per unit on the page
    abstract public BigDecimal getUnitPrice(int numberOfElement);


    //Sort for getting Product obj
    abstract public List<Product> getProductsFromPage(List<String> info);

    // Enter to the search bar text.
    public void searching(String name) {
        WebElement element = driver.findElement(By.id("fti-search"));
        element.sendKeys(name);
        element.sendKeys(Keys.ENTER);
//        waiting(5);
    }

    public void addToCard(Product product) {

        WebElement productDiv = driver.findElement(By.linkText(product.getName()));
//        waiting(15);
        WebElement button = driver.findElement(By.xpath("//button[normalize-space()='" + "Ostukorvi" + "']"));
        button.click();
    }
//Wait method
// TODO: 21-Jul-23 in future remove static


//    public static void waiting(int seconds) {
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(seconds));
//    }


}
