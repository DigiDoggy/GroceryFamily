package com.example.FinalProject.productCheckOnThePage;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NameChecking {
    private WebDriver driver;

    NameChecking(WebDriver driver){
        this.driver=driver;
    }


    public List<String> checkingName(String pageSelector){

        return null;
        }


}
