package com.example.FinalProject.service;

import com.example.FinalProject.model.Grocery;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.stream.Collectors;

public class GroceryInfoService {
    private final GroceryService groceryService;

    public GroceryInfoService(GroceryService groceryService) {
        this.groceryService = groceryService;
    }

    public LinkedHashMap<String, String> getGroceryNameAmount() {

        List<Grocery> groceries =groceryService.findAllGrocery();

        LinkedHashMap<String, String> GroceryNameAmount;
        GroceryNameAmount = groceries.stream().collect(Collectors.toMap(Grocery::getName,
                Grocery::getQuantity, (oldValue, newValue) -> oldValue, LinkedHashMap::new));

        return GroceryNameAmount;
    }


}
