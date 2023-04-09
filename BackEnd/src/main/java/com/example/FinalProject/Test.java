package com.example.FinalProject;

import com.example.FinalProject.service.WebScraperService;

import java.util.ArrayList;
import java.util.List;

public class Test {
    public static void main(String[] args) {
        WebScraperService webScraperService=new WebScraperService();
        webScraperService.scrapeRimi("Täispiim 3,8-4,2%",2);


    }
}
