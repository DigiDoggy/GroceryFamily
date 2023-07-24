package com.example.FinalProject.similarity;


import com.example.FinalProject.model.Product;

import java.math.BigDecimal;
import java.util.*;

public class ProductFilter {

    public static List<Product> containsAllWords(List<Product> products, String substring) {

        String[] subWords = substring.toLowerCase().split("\\s+");
        Arrays.sort(subWords);

        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {
            String[] strWords = product.getName().split("\\s+");
            Arrays.sort(strWords);

            if (Arrays.asList(strWords).containsAll(Arrays.asList(subWords))) {
                filteredProducts.add(product);
                // todo must be deleted
                System.out.println("Added product: " + product.getName() + ", price: " + product.getPricePerUnit());
            }
        }

        return filteredProducts;    }

    //gives the cheapest product
    public static Product cheaperPrice(List<Product> products){
        return products.stream()
                .min(Comparator.comparing(product -> {
                    BigDecimal price = product.getPricePerUnit();
                    return price != null ? price : BigDecimal.ZERO;
                }))
                .orElse(null);
    }
}
