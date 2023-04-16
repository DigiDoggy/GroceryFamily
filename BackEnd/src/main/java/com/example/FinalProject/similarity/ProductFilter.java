package com.example.FinalProject.similarity;

import java.util.ArrayList;
import java.util.List;

public class ProductFilter {
    private BigramMatcher bigramMatcher;

    public ProductFilter() {
        bigramMatcher = new BigramMatcher();
    }

    public List<String> filterProducts(List<String> products, String searchString) {
        List<String> filteredProducts = new ArrayList<>();
        boolean specificSearch = searchString.contains(" ");

        for (String product : products) {
            if (specificSearch) {
                double similarityThreshold = 0.9;
                String bestMatch = bigramMatcher.findBestMatchWithThreshold(searchString, products, similarityThreshold);
                if (bestMatch != null) {
                    filteredProducts.add(bestMatch);
                }
            } else {
                if (product.toLowerCase().contains(searchString.toLowerCase())) {
                    filteredProducts.add(product);
                }
            }
        }

        return filteredProducts;
    }
}
