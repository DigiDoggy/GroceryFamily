package com.example.FinalProject.resource;


import com.example.FinalProject.model.Grocery;
import com.example.FinalProject.service.GroceryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/grocery")
public class GroceryResource {
    private final GroceryService groceryService;

    public GroceryResource(GroceryService groceryService){
        this.groceryService = groceryService;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Grocery>> getAllGrocery(){
        List<Grocery> grocery = groceryService.findAllGrocery();
        return new ResponseEntity<>(grocery, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Grocery> getEmployeeById(@PathVariable("id") Long id){
        Grocery grocery = groceryService.findGroceryByID(id);
        return new ResponseEntity<>(grocery,HttpStatus.OK);
    }
    //Add new employee
    @PostMapping("/add")
    public ResponseEntity<Grocery> addGrocery(@RequestBody Grocery grocery){
        Grocery newGrocery = groceryService.addGrocery(grocery);
        return new ResponseEntity<>(newGrocery, HttpStatus.CREATED);
    }

    //Update employee
    @PutMapping("/update")
    public ResponseEntity<Grocery> updateEmployee(@RequestBody Grocery employee){
        Grocery updateGrocery = groceryService.updateGrocery(employee);
        return new ResponseEntity<>(updateGrocery, HttpStatus.OK);
    }

    //Delete employee
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGrocery(@PathVariable("id") Long id){
        groceryService.deleteGrocery(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/deleteAll")
    public ResponseEntity<Grocery> deleteALlGrocery(){
         groceryService.deleteAllGrocery();
        return new ResponseEntity<>(HttpStatus.OK);

    }


}
