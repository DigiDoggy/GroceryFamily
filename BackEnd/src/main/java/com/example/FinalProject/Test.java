package com.example.FinalProject;


import com.example.FinalProject.service.WebScraperService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;


@SpringBootApplication
public class Test {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(FinalProjectApplication.class, args);
        WebScraperService webScraperService = context.getBean(WebScraperService.class);
        webScraperService.scrapeRimi();

    }
}
