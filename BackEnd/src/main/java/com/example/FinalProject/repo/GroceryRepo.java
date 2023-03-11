package com.example.FinalProject.repo;

import com.example.FinalProject.model.Grocery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface GroceryRepo extends JpaRepository<Grocery, Long> {

    Optional<Grocery> findGroceryById(Long id);


    void deleteGroceryByCode(List<String> groceryCode);
}
