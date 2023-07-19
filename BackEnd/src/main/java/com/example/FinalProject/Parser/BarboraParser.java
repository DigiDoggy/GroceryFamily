package com.example.FinalProject.Parser;

import com.example.FinalProject.model.Product;
import com.example.FinalProject.productCheckOnThePage.NameChecking;
import com.example.FinalProject.service.GroceryInfoService;
import com.example.FinalProject.similarity.ProductFilter;
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

    //todo how best to use classes for so as not to do unnecessary loading. Transfer methods to a class where there is already some kind of functionality or transfer the results to a written method so that it checks further there
    @Override
    public List<Product> getProductsFromPage(List<String> info) {
        List<String> getNamesFromDB = super.getNamesFromDB();
        List<Product> products = new ArrayList<>();

        System.out.println("Barbora parser getProductsFromPage -_------------------------------------- starts");

        int value=0;
        int index = 0;
        for (String jsonString : info) {
            boolean isProductInitialized = false;
            Product product = new Product();

            try {
                ObjectMapper mapper = new ObjectMapper();
                product = mapper.readValue(jsonString, Product.class);

                isProductInitialized = true;
            } catch (Exception e) {
                e.printStackTrace();
            }


            System.out.println("Barbora parser getProductsFromPage -_------------------------------------- starts"+ value);

            if (isProductInitialized) {
                product.setMeasurement(product.getName());
                product.setPricePerUnit(getUnitPrice(index));
                System.out.println("Barbora parser isProductInitialized  -_------------------------------------- " +
                        "starts"+ value);
                value++;


                    products.add(product);

                    index++;

            }


        }

        nameFilter(products, "piim");

        setOnThePage(products);


        return products;
    }

    @Override
    public Product getCheapestProduct(List<Product> products) {
        System.out.println(getCheapestProduct(products)+"------------------------------Cheapest Product " +
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
}
