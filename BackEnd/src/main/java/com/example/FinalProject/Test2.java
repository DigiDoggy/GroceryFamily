package com.example.FinalProject;

import com.example.FinalProject.model.Measurement;
import com.example.FinalProject.model.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static com.example.FinalProject.model.Measurement.setValueUnit;

public class Test2 {
    public static void main(String[] args) {

        String someString = "Piim 2 l";
        String someString2 = "Kartul 223kg";

        Product product = new Product(someString, new BigDecimal("10.0"));

        System.out.println(product.toString());



//
//       Measurement measurement =Measurement.setValueUnit(Measurement.getArrayFromProductName(someString2));
//
//        System.out.println(measurement.toString());



    }


}
