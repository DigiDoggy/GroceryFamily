package com.example.FinalProject.Parser;

import com.example.FinalProject.model.Product;
import com.example.FinalProject.service.GroceryInfoService;
import com.example.FinalProject.similarity.ProductFilter;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//ToDO need coke to log in to the site ***************
// // TODO: 24-Jul-23 clear all abstract methods
public class BarboraParser extends WebParser {

    private final String barboraUrl = "http://www.barbora.ee";


    public BarboraParser(WebDriver driver, GroceryInfoService groceryInfoService) {
        super(driver, groceryInfoService);
    }

    //// TODO: 21-Jul-23 try test 
    @Override
    public void scrapeWebSite() {
        logIntoWebSite(barboraUrl);
        removeCookiePopup();
        List<String> namesFromDB1 = getNamesFromDB();

        for (String grocery: namesFromDB1) {
            System.out.println(grocery);
            searching(grocery);
            List<Product> products = getProductsFromPage(getGroceriesInfoOnThePage("data-b-for-cart"));
            for (Product product: products) {
                System.out.println("getProductsFromPage"+product);
            }

            System.out.println("scrapeWebSite"+products);
            //// TODO: 21-Jul-23 add price to the DB
            Product product = getCheapestProduct(products);
            System.out.println(product);

        }

    }




    //get products from Json

    @Override
    public List<Product> getProductsFromPage(List<String> info) {
        List<Product> products=new ArrayList<>();
        ObjectMapper mapper = new ObjectMapper();


        for (String jsonString : info) {

            try {
                Product product = mapper.readValue(jsonString, Product.class);
                product.setMeasurement(product.getName());
                products.add(product);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }


        nameFilter(products, "piim");
        for (Product product: products) {
            if (product != null){
                System.out.println("nameFilter" + product);
            }else{
                System.out.println("nameFilter don`t work");
            }
        }

        setOnThePage(products);

        for (Product product: onThePage) {
                if (product != null){
                    System.out.println("setOnThePage" + product);
                }else{
                    System.out.println("setOnThePage don`t work");
                }
        }

        return products;
    }

    @Override
    public Product getCheapestProduct(List<Product> products) {
        System.out.println(ProductFilter.cheaperPrice(products));
        return super.getCheapestProduct(products);
    }

    //Searching all price per unit on the page
    public BigDecimal getUnitPrice(int numberOfElement) {
        List<BigDecimal> elements = driver.findElements(By.className("b-product-price--extra"))
                .stream()
                .map(e -> new BigDecimal(e.getText()
                        .replace("€", "")
                        .replaceAll("/.*", "")))
                .toList();


        return elements.get(numberOfElement);
    }

    @Override
    public void addToCard(Product product) {
        super.addToCard(product);
    }

        @Override
    public List<String> getGroceriesInfoOnThePage(String pageSelector) {
        //work
        List<String> elements = driver.findElements(By.cssSelector("[" + pageSelector + "]"))
                .stream()
                .map(e -> e.getAttribute(pageSelector))
                .collect(Collectors.toList());

//            for (String product: elements) {
//                System.out.println(product+"getGroceriesInfoOnThePage");
//            }

        return elements;
    }
}
