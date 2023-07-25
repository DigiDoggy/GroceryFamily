package GroceryFamily.GroceryElders.repo;

import GroceryFamily.GroceryElders.model.Grocery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface GroceryRepo extends JpaRepository<Grocery, UUID> {

    Optional<Grocery> findGroceryByGroceryCode(UUID groceryCode);

    Optional<Grocery> findGroceryByName(String name);

    void deleteByGroceryCode(UUID groceryCode);

    @Query("SELECT g.name FROM Grocery g")
    List<String> findAllGroceryNames();





}
