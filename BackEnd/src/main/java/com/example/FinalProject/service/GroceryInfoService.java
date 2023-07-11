package com.example.FinalProject.service;

import com.example.FinalProject.model.Grocery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GroceryInfoService {
    public GroceryService groceryService;

    @Autowired
    public GroceryInfoService(GroceryService groceryService) {
        this.groceryService = groceryService;
    }

    public List<String> getProductInfoFromDB(){
        List<Grocery> groceries=groceryService.findAllGrocery();
        List<String> products=new ArrayList<>();

        for (Grocery grocery: groceries) {
            String product=grocery.getName();
            products.add(product);
        }
        return products;
    }

    public List<String> getQuantity(){
        List<String> quantities =new ArrayList<>();
        List<Grocery> groceries=groceryService.findAllGrocery();

        for (Grocery grocery: groceries) {
            String amount=grocery.getQuantity();
            quantities.add(amount);
        }
        return quantities;
    }

}
