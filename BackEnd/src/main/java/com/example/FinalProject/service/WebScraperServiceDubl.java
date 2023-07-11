package com.example.FinalProject.service;

import com.example.FinalProject.Parser.BarboraParser;
import com.example.FinalProject.Parser.PrismaParser;
import com.example.FinalProject.Parser.RimiParser;
import com.example.FinalProject.model.Product;

import java.util.List;

public class WebScraperServiceDubl {

    // From parse package
    RimiParser rimiParser = new RimiParser();
    PrismaParser prismaParser= new PrismaParser();
//    BarboraParser barboraParser = new BarboraParser();

    List<Product> rimiProduct = rimiParser.parserProducts();
    List<Product> prismaProduct = prismaParser.parserProducts();
//    List<Product> barboraProduct = barboraParser.parserProducts();

    //another actions
    private void performAdditionalOperations() {

    }

    private void displayResults(){

    }

}