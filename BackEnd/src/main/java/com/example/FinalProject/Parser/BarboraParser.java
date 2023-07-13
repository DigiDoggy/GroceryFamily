package com.example.FinalProject.Parser;

import com.example.FinalProject.model.Product;
import com.example.FinalProject.service.GroceryInfoService;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class BarboraParser extends WebParser{

    private String url="http://www.barbora.ee";


    public BarboraParser(WebDriver driver, GroceryInfoService groceryInfoService) {
        super(driver, groceryInfoService);
    }
}
