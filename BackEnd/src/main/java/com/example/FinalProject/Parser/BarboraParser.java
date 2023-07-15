package com.example.FinalProject.Parser;

import com.example.FinalProject.model.Product;
import com.example.FinalProject.productCheckOnThePage.NameChecking;
import com.example.FinalProject.service.GroceryInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BarboraParser extends WebParser {

    private String url = "http://www.barbora.ee";


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
    public List<Product> getProducts(List<String> info) {
        List<Product> products = new ArrayList<>();

        int index = 0;
        for (String jsonString : info) {

            try {
                ObjectMapper mapper = new ObjectMapper();
                Product product = mapper.readValue(jsonString, Product.class);
                List<BigDecimal> list = new ArrayList<>();

                product.setMeasurement(product.getName());

                product.setPricePerUnit(getUnitPrice(index++));


                products.add(product);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        NameChecking.checkingName(products,getNamesFromDB());
        setOnThePage(products);
        return products;
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
}
