package com.example.FinalProject.similarity;

import java.util.*;

public class BigramMatcher {

    public String findBestMatchWithThreshold(String target, List<String> candidates, double similarityThreshold) {
        return findBestMatch(target, candidates, similarityThreshold);
    }
    public List<String> generateBigrams(String text) {
        List<String> bigrams = new ArrayList<>();
        for (int i = 0; i < text.length() - 1; i++) {
            bigrams.add(text.substring(i, i + 2));
        }
        return bigrams;
    }

    public double calculateSimilarity(String str1, String str2) {
        List<String> bigrams1 = generateBigrams(str1);
        List<String> bigrams2 = generateBigrams(str2);

        Set<String> bigramSet1 = new HashSet<>(bigrams1);
        Set<String> bigramSet2 = new HashSet<>(bigrams2);

        int intersectionSize = 0;
        for (String bigram : bigramSet1) {
            if (bigramSet2.contains(bigram)) {
                intersectionSize++;
            }
        }

        int unionSize = bigramSet1.size() + bigramSet2.size() - intersectionSize;

        return (double) intersectionSize / unionSize;
    }

    public String findBestMatch(String target, List<String> candidates, double similarityThreshold) {
        String bestMatch = null;
        double highestSimilarity = 0;

        for (String candidate : candidates) {
            double similarity = calculateSimilarity(target, candidate);
            if (similarity > highestSimilarity && similarity >= similarityThreshold) {
                highestSimilarity = similarity;
                bestMatch = candidate;
            }
        }

        return bestMatch;
    }

    public Map<String, String> matchProducts(List<String> targetProducts, List<String> candidateProducts, double similarityThreshold) {
        Map<String, String> matchedProducts = new HashMap<>();
        for (String targetProduct : targetProducts) {
            String bestMatch = findBestMatch(targetProduct, candidateProducts, similarityThreshold);
            if (bestMatch != null) {
                matchedProducts.put(targetProduct, bestMatch);
            }
        }
        return matchedProducts;
    }
}
