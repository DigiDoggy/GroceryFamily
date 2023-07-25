package GroceryFamily.GroceryMom.resource;

import GroceryFamily.GroceryElders.model.Grocery;
import GroceryFamily.GroceryElders.repo.GroceryRepo;
import GroceryFamily.GroceryElders.service.GroceryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/grocery")
public class GroceryResource {
    private final GroceryService groceryService;
    private final GroceryRepo groceryRepo;

    public GroceryResource(GroceryService groceryService,
                           GroceryRepo groceryRepo) {
        this.groceryService = groceryService;
        this.groceryRepo = groceryRepo;
    }


    @GetMapping("/all")
    public ResponseEntity<List<Grocery>> getAllGrocery() {
        List<Grocery> grocery = groceryService.findAllGrocery();
        return new ResponseEntity<>(grocery, HttpStatus.OK);
    }

    @GetMapping("/find/{id}")
    public ResponseEntity<Grocery> getEmployeeById(@PathVariable("id") UUID groceryCode) {
        Grocery grocery = groceryService.findGroceryByID(groceryCode);
        return new ResponseEntity<>(grocery, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addGrocery(@RequestBody List<Grocery> groceries) {
        try {
            List<Grocery> savedGroceries = groceryService.addGrocery(groceries);
            return new ResponseEntity<>(savedGroceries, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //Update
    @PutMapping("/update")
    public ResponseEntity<Grocery> updateEmployee(@RequestBody Grocery grocery) {
        Grocery updateGrocery = groceryService.updateGrocery(grocery);
        return new ResponseEntity<>(updateGrocery, HttpStatus.OK);
    }

    @DeleteMapping("/{groceryCode}")
    public ResponseEntity<Void> deleteGrocery(@PathVariable UUID groceryCode) {
        Optional<Grocery> groceryOptional = Optional.ofNullable(groceryService.findGroceryByID(groceryCode));

        if (groceryOptional.isPresent()) {
            groceryService.deleteById(groceryCode);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }


}
