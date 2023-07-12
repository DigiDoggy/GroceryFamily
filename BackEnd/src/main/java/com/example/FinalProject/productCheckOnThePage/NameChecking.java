package com.example.FinalProject.productCheckOnThePage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class NameChecking {
    private WebDriver driver;

    NameChecking(WebDriver driver){
        this.driver=driver;
    }


    public void checkingName(String name,String pageSelector){
        WebElement productBox= driver.findElement(By.id(pageSelector));

    }
}
