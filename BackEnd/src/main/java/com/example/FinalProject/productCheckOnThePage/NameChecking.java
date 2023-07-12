package com.example.FinalProject.productCheckOnThePage;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class NameChecking {
    private WebDriver driver;

    NameChecking(WebDriver driver){
        this.driver=driver;
    }


    public void checkingName(String pageSelector){
        List<String> elements = driver.findElements(By.cssSelector("["+pageSelector+"]"))
                .stream()
                .map(e->e.getAttribute(pageSelector))
                .collect(Collectors.toList());

        elements.forEach(System.out::println);

        }
//        List<WebElement> elements= driver.findElements(By.tagName(pageSelector));
//        for (WebElement info: elements) {
//            if(info.equals(null)){
//                System.out.println("potter we have a problem");
//            }
//            System.out.println(info.toString());
//        }

//    }

    public static void main(String[] args) {
        NameChecking nameChecking;
//        nameChecking.checkingName("http://www.rimi.ee", "piim");
    }
}
