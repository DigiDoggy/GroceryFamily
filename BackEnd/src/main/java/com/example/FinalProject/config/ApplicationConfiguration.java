package com.example.FinalProject.config;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.time.Duration;

@Configuration
public class ApplicationConfiguration {

    @Bean
    public WebDriver chromeDriver() {
        System.setProperty("webdriver.chrome.driver", "C:/path/to/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        WebDriver driver = new ChromeDriver(options);
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        return driver;

//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--whitelisted-ips=127.0.0.1");
//        WebDriver driver = new ChromeDriver(options);
    }
    public static void waiting(int seconds){
        WebDriver driver=new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(seconds));
    }

}
