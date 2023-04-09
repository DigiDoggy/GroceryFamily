package com.example.FinalProject.service;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.lang.System;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class WebScraperService {

    String rimi_URL = "https://www.rimi.ee/epood/ee/";
    String prisma_URL = "https://www.prisma.ee/";
    String barbora_URL = "https://www.barbora.ee/";
    private WebDriver driver;

    public WebScraperService(){
        System.setProperty("webdriver.chrome.driver","C:/path/to/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        this.driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10)); // Implicit wait 5 seconds

    }

    public void scrapeRimi(String productName, int quantity){
        driver.get(rimi_URL);

        //searching
        WebElement searchInput = driver.findElement(By.id("search-input"));
        searchInput.sendKeys(productName); // for example "Täispiim 3,8-4,2%"
        searchInput.submit();// trying that way
//        searchInput.sendKeys(Keys.ENTER);  // second way

        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("distill")));

        // Get product list
//        List<WebElement> productList = driver.findElements(By.cssSelector(".product-grid__item"));
//
//        Map<String, Integer> productMap = new HashMap<>();
//
//        for (WebElement product : productList) {
//            String productTitle = product.findElement(By.cssSelector(".product-card__name")).getText();
//            String productPrice = product.findElement(By.cssSelector(".price-box__price")).getText();
//            String productLink = product.findElement(By.cssSelector("a")).getAttribute("href");
//
//            int price = parsePrice(productPrice);
//            productMap.put(productLink, price);
//        }
//        List<WebElement> productList = driver.findElements(By.cssSelector(".card.-horizontal-for-mobile"));
//        List<WebElement> productList;
        List<WebElement> productList = driver.findElements(By.cssSelector(".product-grid__item"));
//        productList = driver.findElements(By.id(".product-grid_item .card_name ").findElement(productName));

        if(productList.isEmpty()){
            System.out.println("Error with product LIST **********************************");
            driver.close();
        }else{
            for (WebElement element : productList) {
                System.out.println(element.toString() + "************************************");
            }
        }
        Map<String, Double> productMap = new HashMap<>();

        for (WebElement product : productList) {
            WebElement pricePer = product.findElement(By.cssSelector(".card__price-per"));
            String pricePerText = pricePer.getText();
            System.out.println(pricePerText+ "/////////////////////////////////////////////////////////");
            String[] pricePerParts = pricePerText.split("\\s+");
            double price = Double.parseDouble(pricePerParts[0].replace(",", "."));
            String priceUnit = pricePerParts[2];

            System.out.println(price+ "---------------------------------------------------");

            WebElement button = product.findElement(By.cssSelector("button[data-gtm-event-category='addToBasket']"));
            String id = button.getAttribute("data-gtm-product-id");
            System.out.println("Button ID: " + id);

            System.out.println(id +" --------------------------------------------------");

            productMap.put(id,price);

        }

        //find the cheapest product
        String cheapestProductButtonId=null;
        Double cheapestProductPrice= Double.MAX_VALUE;

        for (Map.Entry<String, Double> entry: productMap.entrySet()) {

            if (entry.getValue()<cheapestProductPrice){
                cheapestProductButtonId= entry.getKey();
                cheapestProductPrice= entry.getValue();
            }
        }
        System.out.println("--------------------------------");
        System.out.println(cheapestProductButtonId);
        System.out.println(cheapestProductPrice);
        System.out.println("--------------------------------");

        // Go to the cheapest product page and add the desired quantity to the cart
        if(cheapestProductButtonId!=null){


            WebElement addToCartButton = driver.findElement(By.cssSelector("button[data-gtm-product-id='"+cheapestProductButtonId+"']"));
            addToCartButton.click();


        }




        if(!productMap.isEmpty()){
            for (Map.Entry<String, Double> entry : productMap.entrySet()) {
                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
            }
        }else {
            System.out.println("ERROR MAP IS EMPTY ");
        }


    }
    public int parsePrice(String priceString) throws NumberFormatException {
        if (priceString == null || priceString.isEmpty()) {
            throw new IllegalArgumentException("Price string cannot be null or empty.");
        }

        String normalizedPriceString = priceString.replaceAll(",", ".");
        Matcher matcher = Pattern.compile("(\\d+\\.\\d{1,2})").matcher(normalizedPriceString);

        if (matcher.find()) {
            double priceDouble = Double.parseDouble(matcher.group(1));
            return (int) (priceDouble * 100);
        } else {
            throw new NumberFormatException("Price string does not contain a valid price.");
        }
    }

}
