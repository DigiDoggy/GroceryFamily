package com.example.FinalProject.Parser;

import ch.qos.logback.core.encoder.EchoEncoder;
import com.example.FinalProject.model.Product;
import com.example.FinalProject.service.GroceryInfoService;
import com.example.FinalProject.service.GroceryService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    public List<String> allProductsOnThePage(){
        //For barboraParser
//        List<String> elements = driver.findElements(By.cssSelector("["+pageSelector+"]"))
//                .stream()
//                .map(e->e.getAttribute(pageSelector))
//                .collect(Collectors.toList());
//
//        elements.forEach(System.out::println);
        return null;
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

    //Mb method only for barboraParser( work with Json)
    public List<Product> getProducts(List<String> json){
        List<Product> products= new ArrayList<>();

        for (String jsonString: json) {
            try{
                ObjectMapper mapper = new ObjectMapper();
                Product product = mapper.readValue(jsonString,Product.class);
                products.add(product);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return products;
    }
    void addToCard() {
    }


}
