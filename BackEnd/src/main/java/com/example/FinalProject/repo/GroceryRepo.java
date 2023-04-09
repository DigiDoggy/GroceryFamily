package com.example.FinalProject.repo;

import com.example.FinalProject.model.Grocery;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface GroceryRepo extends JpaRepository<Grocery, UUID> {

    Optional<Grocery> findGroceryByGroceryCode(UUID groceryCode);

    Optional<Grocery> findGroceryByName(String name);

    void deleteByGroceryCode(UUID groceryCode);



}
