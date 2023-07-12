package com.example.FinalProject;



import com.example.FinalProject.productCheckOnThePage.NameChecking;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


import java.util.List;


@SpringBootApplication
public class Test {
    public static void main(String[] args) {

        ConfigurableApplicationContext context = SpringApplication.run(Test.class, args);
        NameChecking nameChecking = context.getBean(NameChecking.class);


        WebDriver driver=context.getBean(WebDriver.class);

        driver.get("https://barbora.ee/otsing?q=piim");
        WebElement element =
                driver.findElement(By
                        .id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll"));

        element.click();

        nameChecking.checkingName("data-b-for-cart");

        driver.quit();


    }
}
