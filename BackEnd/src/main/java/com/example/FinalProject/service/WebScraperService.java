package com.example.FinalProject.service;

import com.example.FinalProject.model.Grocery;
import com.example.FinalProject.model.Measurement;
import com.example.FinalProject.model.Product;
//import com.example.FinalProject.similarity.ProductFilter;
import com.example.FinalProject.similarity.ProductFilter;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class WebScraperService {

    // filter for product by name
//    private ProductFilter productFilter;
    private String productNameFromDB;

    //getters and setters
    public String getProductNameFromDB() {
        return productNameFromDB;
    }

    public void setProductNameFromDB(String productNameFromDB) {
        this.productNameFromDB = productNameFromDB;
    }


    // get data from db
    private GroceryService groceryService;


    // pages for scraping
    String rimi_URL = "https://www.rimi.ee/epood/ee/";
    String prisma_URL = "https://www.prisma.ee/";
    String barbora_URL = "https://www.barbora.ee/";

    @Autowired
    private WebDriver driver;

    //constructor to get data from db
    public WebScraperService(GroceryService groceryService, WebDriver driver) {
        this.groceryService = groceryService;
        this.driver = driver;
    }

    // apply cookie
    private void applyCookie(String text) {
        WebElement element = driver.findElement(By.linkText(text));
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        element.click();
    }

    //enter the product name to search line
    private void searchProduct(String productName) {
        WebElement searchInput = driver.findElement(By.id("search-input"));
        searchInput.sendKeys(productName);
        searchInput.submit();
    }

    // Get Collection with product name and Quantity
    private LinkedHashMap<String, String> getProductAndQuantity() {

        List<Grocery> groceries = groceryService.findAllGrocery();

        LinkedHashMap<String, String> productAndQuantity = new LinkedHashMap<>();

        for (Grocery product : groceries) {
            productAndQuantity.put(product.getName(), product.getQuantity());
        }
        return productAndQuantity;
    }


    public void scrapeRimi() {

        driver.get(rimi_URL);


//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        Thread wait = new Thread();
        try {
            wait.sleep(500);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        applyCookie("Kinnita kõik");

        LinkedHashMap<String, String> productAndQuantity = getProductAndQuantity();


        for (Map.Entry<String, String> entry : productAndQuantity.entrySet()) {
            String productName = entry.getKey();
            setProductNameFromDB(productName);
            System.out.println(getProductNameFromDB());
            String productQuantity = entry.getValue();

            System.out.println(productQuantity);


            List<Product> products = new ArrayList<>();

            System.out.println(productNameFromDB);
            searchProduct(productName);
            //todo remove the link field from the product, I'll make it easier. After the lowest unit price (measurement), I will return to the most profitable product and click on the link to find the words in the desired tag.
            listOfProducts(products, productNameFromDB);


//            products.add(getNewProduct());


        }
    }

    //Creating method which create product(find price per unit, and name)
//    public Product getNewProduct() {
//
//        WebElement webElement1 = driver.findElement(By.xpath("//*[@id=\"main\"]/section/div[1]/div/div[2]/div[1]/div/div[2]/ul/li[1]/div/div[3]/p[1]"));
//        String nameOfProduct = webElement1.getText();
//        String priceString = driver.findElement(By.xpath("//*[@id=\"main\"]/section/div[1]/div/div[2]/div[1]/div/div[2]/ul/li[1]/div/div[3]/div/div/div[2]/p")).getText();
//        //take string example (1.00 eur/l)- and create 1,00 for BigDecimal() method
//        String price = priceString.split(" ")[0].replace(',', '.');
//        System.out.println(price);
//        Product newProduct = new Product(nameOfProduct, new BigDecimal(price));
//        return newProduct;
//    }

    //Checks for similarity and adds products to array
    public List<Product> listOfProducts(List<Product> products, String productNameFromDB) {

        for (int i = 1; i <= 20; i++) {
            String productName = driver.findElement(By.xpath("//*[@id=\"main\"]/section/div[1]/div/div[2]/div[1]/div/div[2]/ul/li[" + i + "]/div/div[3]/p[1]")).getText().toLowerCase();
            String priceStringPerUnit = driver.findElement(By.xpath("//*[@id=\"main\"]/section/div[1]/div/div[2]/div[1]/div/div[2]/ul/li[" + i + "]/div/div[3]/div/div/div[2]/p")).getText();
            String priceString = driver.findElement(By.xpath("//*[@id=\"main\"]/section/div[1]/div/div[2]/div[1]/div/div[2]/ul/li["+ i +"]/div/div[3]/div/div/div[1]/span")).getText() +
                    "." +
                    driver.findElement(By.xpath("//*[@id=\"main\"]/section/div[1]/div/div[2]/div[1]/div/div[2]/ul/li["+ i + "]/div/div[3]/div/div/div[1]/div/sup")).getText();
            String addToCardPage = driver.findElement(By.xpath("//*[@id=\"main\"]/section/div[1]/div/div[2]/div[1]/div/div[2]/ul/li["+i+"]/div/div[3]/div/form[2]/button")).getText();

            //take string example (1.00 eur/l)- and create 1,00 for BigDecimal() method

            String productPricePerUnit = priceStringPerUnit.split(" ")[0].replace(',', '.');

            //Checking for similarity
            if (ProductFilter.containsAllWords(productName, productNameFromDB)) {
                Product product = new Product(productName, new BigDecimal(productPricePerUnit), new BigDecimal(priceString),addToCardPage);
                System.out.println(productName + "Price: " + productPricePerUnit + " page: " + addToCardPage );
                products.add(product);
            }

        }
        return products;
    }


}


// Найти элемент по id
//        WebElement element = driver.findElement(By.id("CybotCookiebotDialog"));
//
//        // Получить текст элемента и вывести его в консоль
//        String text = element.getText();
//        System.out.println("Text of the element: " + text);
//
//        // Закрыть браузер
//        driver.quit();

//        WebElement bodyTag = driver.findElement(By.tagName("body"));
//        String clasName= bodyTag.getAttribute("class");
//
//        System.out.println(clasName+ "_______________________________________________________");


//        //searching
//        WebElement searchInput = driver.findElement(By.id("search-input"));
//        searchInput.sendKeys(productName); // for example "Täispiim 3,8-4,2%"
//        searchInput.submit();// trying that way
////        searchInput.sendKeys(Keys.ENTER);  // second way
//
//
//
//        // Get product list
////        List<WebElement> productList = driver.findElements(By.cssSelector(".product-grid__item"));
////
////        Map<String, Integer> productMap = new HashMap<>();
////
////        for (WebElement product : productList) {
////            String productTitle = product.findElement(By.cssSelector(".product-card__name")).getText();
////            String productPrice = product.findElement(By.cssSelector(".price-box__price")).getText();
////            String productLink = product.findElement(By.cssSelector("a")).getAttribute("href");
////
////            int price = parsePrice(productPrice);
////            productMap.put(productLink, price);
////        }
////        List<WebElement> productList = driver.findElements(By.cssSelector(".card.-horizontal-for-mobile"));
////        List<WebElement> productList;
//        List<WebElement> productList = driver.findElements(By.cssSelector(".product-grid__item"));
////        productList = driver.findElements(By.id(".product-grid_item .card_name ").findElement(productName));
//
//        if(productList.isEmpty()){
//            System.out.println("Error with product LIST **********************************");
//            driver.close();
//        }else{
//            for (WebElement element : productList) {
//                System.out.println(element.toString() + "************************************");
//            }
//        }
//        Map<String, Double> productMap = new HashMap<>();
//
//        for (WebElement product : productList) {
//            WebElement pricePer = product.findElement(By.cssSelector(".card__price-per"));
//            String pricePerText = pricePer.getText();
//            System.out.println(pricePerText+ "/////////////////////////////////////////////////////////");
//            String[] pricePerParts = pricePerText.split("\\s+");
//            double price = Double.parseDouble(pricePerParts[0].replace(",", "."));
//            String priceUnit = pricePerParts[2];
//
//            System.out.println(price+ "---------------------------------------------------");
//
//            WebElement button = product.findElement(By.cssSelector("button[data-gtm-event-category='addToBasket']"));
//            String id = button.getAttribute("data-gtm-product-id");
//            System.out.println("Button ID: " + id);
//
//            System.out.println(id +" --------------------------------------------------");
//
//            productMap.put(id,price);
//
//        }
//
//        //find the cheapest product
//        String cheapestProductButtonId=null;
//        Double cheapestProductPrice= Double.MAX_VALUE;
//
//        for (Map.Entry<String, Double> entry: productMap.entrySet()) {
//
//            if (entry.getValue()<cheapestProductPrice){
//                cheapestProductButtonId= entry.getKey();
//                cheapestProductPrice= entry.getValue();
//            }
//        }
//        System.out.println("--------------------------------");
//        System.out.println(cheapestProductButtonId);
//        System.out.println(cheapestProductPrice);
//        System.out.println("--------------------------------");
//
//        // Go to the cheapest product page and add the desired quantity to the cart
//        if(cheapestProductButtonId!=null){
//
//
//            WebElement addToCartButton = driver.findElement(By.cssSelector("button[data-gtm-product-id='"+cheapestProductButtonId+"']"));
//            addToCartButton.click();
//
//
//        }
//
//
//
//
//        if(!productMap.isEmpty()){
//            for (Map.Entry<String, Double> entry : productMap.entrySet()) {
//                System.out.println("Key: " + entry.getKey() + ", Value: " + entry.getValue());
//            }
//        }else {
//            System.out.println("ERROR MAP IS EMPTY ");
//        }
//
//
//    }
//    public int parsePrice(String priceString) throws NumberFormatException {
//        if (priceString == null || priceString.isEmpty()) {
//            throw new IllegalArgumentException("Price string cannot be null or empty.");
//        }
//
//        String normalizedPriceString = priceString.replaceAll(",", ".");
//        Matcher matcher = Pattern.compile("(\\d+\\.\\d{1,2})").matcher(normalizedPriceString);
//
//        if (matcher.find()) {
//            double priceDouble = Double.parseDouble(matcher.group(1));
//            return (int) (priceDouble * 100);
//        } else {
//            throw new NumberFormatException("Price string does not contain a valid price.");
//        }
//    }


