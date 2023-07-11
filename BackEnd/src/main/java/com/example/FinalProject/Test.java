package com.example.FinalProject;


import com.example.FinalProject.Parser.BarboraParser;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;



public class Test {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
       BarboraParser barbora=new BarboraParser(driver);
       barbora.scrapeWebSite();

    }
}
