package com.example.FinalProject.service;


import com.example.FinalProject.model.Grocery;
import com.example.FinalProject.repo.GroceryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.Entity;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;

@Service
@Transactional
public class GroceryService{
    private final GroceryRepo groceryRepo;
    @Autowired
    public GroceryService(GroceryRepo groceryRepo){
        this.groceryRepo = groceryRepo;
    }

    public Grocery addGrocery(Grocery grocery){
        return groceryRepo.save(grocery);
    }

    public List<Grocery> findAllGrocery(){
        return groceryRepo.findAll();
    }

    public Grocery updateGrocery(Grocery grocery){
        return groceryRepo.save(grocery);
    }

    public Grocery findGroceryByID(Long id){
        return groceryRepo.findGroceryById(id).orElseThrow(
                ()-> new RuntimeException("User by id " + id + "was not found")
        );
    }

    public void deleteGrocery(Long id){
        groceryRepo.deleteGroceryById(id);
    }
  @Transactional
    public void deleteAllGrocery(){
      groceryRepo.deleteAll();
  }


}