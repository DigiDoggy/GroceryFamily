package com.example.FinalProject.Parser;

import com.example.FinalProject.model.Product;
import com.example.FinalProject.service.GroceryInfoService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class BarboraParser extends WebParser{

    private String url="http://www.barbora.ee";


    public BarboraParser(WebDriver driver, GroceryInfoService groceryInfoService) {
        super(driver, groceryInfoService);
    }

    @Override
    public List<String> getGroceriesInfoOnThePage(String pageSelector) {
        List<String> elements = driver.findElements(By.cssSelector("["+pageSelector+"]"))
                .stream()
                .map(e->e.getAttribute(pageSelector))
                .collect(Collectors.toList());

        return elements;
    }

    //get products from Json
    @Override
    public List<Product> getProducts(List<String> info) {
        List<Product> products= new ArrayList<>();

        for (String jsonString: info) {
            try{
                ObjectMapper mapper = new ObjectMapper();
                Product product = mapper.readValue(jsonString,Product.class);

                BigDecimal parsePricePerUnit =new BigDecimal(driver.findElement(By
                                .className("b-product-price--extra"))
                        .getText().replace("€","")
                        .replaceAll("/.*",""));

                //Todo you need to correct the pattern, if there is a space after the comma and the number is written together with the letter symbol, then there is a space between them (example: Piim ALMA 2.5%, 0.5L). If there is a dot after the alphabetic character and then a digit, then after the dot there is a space and after the digit (example 1: Piim.põh.piimasegu APTAMIL 1 al.sün.800g \
                //Todo example 2: Piim VÄIKE TOM UHT maasika&vitam.,200ml)
                product.setMeasurement(product.getName());

                product.setPricePerUnit(parsePricePerUnit);
                products.add(product);
            }catch (Exception e){
                e.printStackTrace();
            }
        }

        return products;
    }
}
