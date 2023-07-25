package GroceryFamily.GroceryDad.service;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class PriceComparisonService {


    public static void main(String[] args) {



          String rimi_URL = "https://www.rimi.ee/epood/ee/";
         String prisma_URL = "https://www.prisma.ee/";
          String barbora_URL = "https://www.barbora.ee/";

        System.setProperty("webdriver.chrome.driver","C:/path/to/chromedriver.exe");
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        ChromeDriver driver = new ChromeDriver(options);
        driver.get(rimi_URL);
        //searching
        WebElement searchInput = driver.findElement(By.id("search-input"));
        //input.
        searchInput.sendKeys("Täispiim 3,8-4,2%");
        searchInput.sendKeys(Keys.ENTER);

    }
}

