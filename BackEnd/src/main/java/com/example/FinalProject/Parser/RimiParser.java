package com.example.FinalProject.Parser;

import com.example.FinalProject.service.GroceryInfoService;
import org.openqa.selenium.WebDriver;


public class RimiParser extends WebParser{
    private final String rimiUrl = "rimi.ee";


    public RimiParser(WebDriver driver, GroceryInfoService groceryInfoService) {
        super(driver, groceryInfoService);
    }
}
