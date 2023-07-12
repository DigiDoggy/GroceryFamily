package com.example.FinalProject;



import com.example.FinalProject.service.GroceryInfoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


import java.util.List;


@SpringBootApplication
public class Test {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Test.class, args);
        GroceryInfoService groceryInfoService = context.getBean(GroceryInfoService.class);


        List<String> productInfoFromDB = groceryInfoService.getProductInfoFromDB();


        productInfoFromDB.forEach(System.out::println);

    }
}
