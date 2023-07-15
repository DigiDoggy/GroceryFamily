package com.example.FinalProject.productCheckOnThePage;

import com.example.FinalProject.model.Product;
import com.example.FinalProject.similarity.ProductFilter;
import java.util.List;


public class NameChecking {

    //check what names of product we have on the page, if in collection list of Products from the page don`t contains
    // similar name like in DB, then remove
    public static List<Product> checkingName(List<Product> onThePage, List<String> groceriesName) {

        int index = 0;

        for (Product product : onThePage) {
            boolean contains = ProductFilter.containsAllWords(product.getName(), groceriesName.get(index));

            if (!contains) {
                onThePage.remove(product);
            }
        }

        return onThePage;
    }



}
