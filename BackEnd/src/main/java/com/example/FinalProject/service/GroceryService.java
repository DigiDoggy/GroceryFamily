package com.example.FinalProject.service;


import com.example.FinalProject.model.Grocery;
import com.example.FinalProject.repo.GroceryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.*;

@Service
@Transactional
public class GroceryService{
    private final GroceryRepo groceryRepo;
    @Autowired
    public GroceryService(GroceryRepo groceryRepo){
        this.groceryRepo = groceryRepo;
    }


    public List<Grocery> addGrocery(List<Grocery> groceries) {
        List<Grocery> savedGroceries = new ArrayList<>();

        for (Grocery grocery : groceries) {
            Optional<Grocery> existingGrocery = groceryRepo.findGroceryByName(grocery.getName());
            if (existingGrocery.isEmpty()) {
                // If there is no such element, add it to the database
                grocery.setGroceryCode(UUID.randomUUID());
                savedGroceries.add(groceryRepo.save(grocery));
            } else {
                existingGrocery.get().setQuantity(grocery.getQuantity());
                savedGroceries.add(groceryRepo.save(existingGrocery.get()));
            }
        }
        return savedGroceries;
    }



    public List<Grocery> findAllGrocery(){
        return groceryRepo.findAll();
    }



    public Grocery updateGrocery(Grocery grocery){
        return groceryRepo.save(grocery);
    }

    public Grocery findGroceryByID(UUID groceryCode){
        return groceryRepo.findGroceryByGroceryCode(groceryCode).orElseThrow(
                ()-> new RuntimeException("User by id " + groceryCode + "was not found")
        );
    }

    public void deleteById(UUID groceryCode) {
        groceryRepo.deleteByGroceryCode(groceryCode);
    }


}