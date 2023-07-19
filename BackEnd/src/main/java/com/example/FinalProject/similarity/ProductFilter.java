package com.example.FinalProject.similarity;


import com.example.FinalProject.model.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ProductFilter {

    public static List<Product> containsAllWords(List<Product> products, String substring) {
        String[] subWords = substring
                .toLowerCase()
                .split("\\s+");
        Arrays.sort(subWords);

        for (Product product: products) {
            String[] strWords = product
                    .getName()
                    .split("\\s+");
            Arrays.sort(strWords);

            if(!Arrays.asList(strWords).containsAll(Arrays.asList(subWords))){
                products.remove(product);
            }
            //todo must be deleted
            System.out.println("Added product: " + product.getName() + ", price: " + product.getPricePerUnit());
        }

        return products;
    }

    //gives the cheapest product
    public static Product cheaperPrice(List<Product> products){

        Product cheapest=products.stream()
                .min(Comparator.comparing(Product::getPrice))
                .orElse(null);

        System.out.println(products+"cheapest product _________________------------------____________");

        return cheapest;
    }


}
