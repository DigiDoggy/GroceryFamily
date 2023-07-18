package com.example.FinalProject.similarity;


import com.example.FinalProject.model.Product;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ProductFilter {

    public static boolean containsAllWords(String str, String substring) {
        String[] strWords = str.split("\\s+");
        String[] subWords = substring.split("\\s+");
        Arrays.sort(strWords);
        Arrays.sort(subWords);
        return Arrays.asList(strWords).containsAll(Arrays.asList(subWords));
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
