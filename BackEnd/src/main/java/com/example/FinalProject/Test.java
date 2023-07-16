package com.example.FinalProject;




import com.example.FinalProject.Parser.BarboraParser;
import com.example.FinalProject.Parser.RimiParser;
import com.example.FinalProject.model.Product;
import com.example.FinalProject.productCheckOnThePage.NameChecking;

import com.example.FinalProject.service.GroceryInfoService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


import java.util.List;


@SpringBootApplication
public class Test {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Test.class, args);
        GroceryInfoService groceryInfoService = context.getBean(GroceryInfoService.class);


        WebDriver driver=context.getBean(WebDriver.class);

        driver.get("https://barbora.ee/otsing?q=piim");
//        https://barbora.ee/otsing?q=piim
        //https://www.rimi.ee/epood/ee/otsing?query=T%C3%A4ispiim
        WebElement element =
                driver.findElement(By
                        .id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll"));


        element.click();



        BarboraParser barboraParser = new BarboraParser(driver,groceryInfoService);
        List<Product> products = barboraParser.getProductsFromPage(barboraParser.getGroceriesInfoOnThePage("data-b-for" +
                "-cart"));


        for (Product product: products) {
            System.out.println(product);;
        }

        driver.quit();


    }
}
