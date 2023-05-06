package com.example.FinalProject.similarity;


import java.util.Arrays;

public class ProductFilter {

    public static boolean containsAllWords(String str, String substring) {
        String[] strWords = str.split("\\s+");
        String[] subWords = substring.split("\\s+");
        Arrays.sort(strWords);
        Arrays.sort(subWords);
        return Arrays.asList(strWords).containsAll(Arrays.asList(subWords));
    }

}
