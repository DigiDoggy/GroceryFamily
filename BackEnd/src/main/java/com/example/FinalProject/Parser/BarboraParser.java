package com.example.FinalProject.Parser;

import com.example.FinalProject.model.Product;
import com.example.FinalProject.service.GroceryInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BarboraParser extends WebParser {

    private final String url = "http://www.barbora.ee";


    public BarboraParser(WebDriver driver, GroceryInfoService groceryInfoService) {
        super(driver, groceryInfoService);
    }

    @Override
    public List<String> getGroceriesInfoOnThePage(String pageSelector) {
        List<String> elements = driver.findElements(By.cssSelector("[" + pageSelector + "]"))
                .stream()
                .map(e -> e.getAttribute(pageSelector))
                .collect(Collectors.toList());

        return elements;
    }

    //get products from Json

    @Override
    public List<Product> getProductsFromPage(List<String> info) {
        List<String> getNamesFromDB = super.getNamesFromDB();
        List<Product> products = new ArrayList<>();
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

        setOnThePage(products);


        return products;
    }

    @Override
    public Product getCheapestProduct(List<Product> products) {
        System.out.println(getCheapestProduct(products) + "------------------------------Cheapest Product " +
                "----------------------");
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
}
