package com.example.FinalProject.Parser;

import com.example.FinalProject.service.GroceryInfoService;

abstract class WebParser {
    private GroceryInfoService groceryInfoService;

    public WebParser(GroceryInfoService groceryInfoService){
        this.groceryInfoService=groceryInfoService;
    }

    void logIntoWebSite(String url){
        groceryInfoService.getGroceryNameAmount();
    }

}
