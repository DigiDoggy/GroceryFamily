package GroceryFamily.GroceryDad.service;
//
//import com.example.FinalProject.model.Grocery;
//import com.example.FinalProject.model.Measurement;
//import com.example.FinalProject.model.Product;
//
//import com.example.FinalProject.similarity.ProductFilter;
//import org.openqa.selenium.*;
//import org.openqa.selenium.support.ui.ExpectedConditions;
//import org.openqa.selenium.support.ui.WebDriverWait;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import java.math.BigDecimal;
//import java.time.Duration;
//import java.util.*;
//
//import static com.codeborne.selenide.Selenide.$;
//
////  Understand why sometimes a product is added to the cart, and sometimes not. (Check maybe you need to check to add to cart)
//
//@Service
public class WebScraperService {
//    private Grocery grocery;
//
//    // filter for product by name
//
//    private String productNameFromDB;
//    private String productQuantity;
//
//    private int rimiPrice;
//
//    //getters and setters
//
//    public String getProductQuantity() {
//        return productQuantity;
//    }
//
//    public void setProductQuantity(String productQuantity) {
//        this.productQuantity = productQuantity;
//    }
//
//    public String getProductNameFromDB() {
//        return productNameFromDB;
//    }
//
//    public void setProductNameFromDB(String productNameFromDB) {
//        this.productNameFromDB = productNameFromDB;
//    }
//
//
//    // get data from db
//    private GroceryService groceryService;
//
//
//    // pages for scraping
//    String rimi_URL = "https://www.rimi.ee/epood/ee/";
//    String prisma_URL = "https://www.prisma.ee/";
//    String barbora_URL = "https://www.barbora.ee/";
//
//    @Autowired
//    private WebDriver driver;
//
//    //constructor to get data from db
//    public WebScraperService(GroceryService groceryService, WebDriver driver) {
//        this.groceryService = groceryService;
//        this.driver = driver;
//    }
//
//    // apply cookie
//    private void applyCookie(String text) {
////        WebElement element = driver.findElement(By.linkText(text));
//        WebElement element = driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll"));
//
//        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        element.click();
//    }
//
//    //enter the product name to search line
//    private void searchProduct(String productName) {
//        WebElement searchInput = driver.findElement(By.id("search-input"));
//        searchInput.sendKeys(productName);
//        searchInput.submit();
//    }
//
//    // Get Collection with product name and Quantity
//    private LinkedHashMap<String, String> getProductAndQuantity() {
//
//        List<Grocery> groceries = groceryService.findAllGrocery();
//
//        LinkedHashMap<String, String> productAndQuantity = new LinkedHashMap<>();
//
//        for (Grocery product : groceries) {
//            productAndQuantity.put(product.getName(), product.getQuantity());
//        }
//        return productAndQuantity;
//    }
//
//
//
//    public void addTheRightAmountOfProducts(Product product,String productQuantity){
//        if (productQuantity!= null && !productQuantity.isEmpty()){
//            String unit = product.getMeasurement()
//                    .getUnit();
//
//            int valueFromProduct
//                    = Integer.parseInt(
//                    product.getMeasurement()
//                            .getValue()
//            );
//
//            Measurement dbMeasurement = Measurement.setValueUnit(Measurement.getArrayFromProductName(productQuantity));
//            int groceryValue = Integer.parseInt(dbMeasurement.getValue());
//            System.out.println(product.getPathToAddMore());
//            WebElement element = driver.findElement(By.xpath(product.getPathToAddMore()));
//            System.out.println("Включение добавления дополнительного товара");
////            Measurement groceryMeasurement = Measurement.setValueUnit(Measurement.getArrayFromProductName(productQuantity));
//            if(productQuantity.contains("tk")||dbMeasurement.getUnit().isEmpty()){
//                System.out.println(groceryValue + "количество сырков");
//                ifGroceryUnitIsTK(groceryValue,product);
//            }else {
//                System.out.println("должно считать количество");
//                ifGroceryIsKgGLMl(product, productQuantity, element, groceryValue, valueFromProduct, unit);
//
//            }
//
//        }else {
//            System.out.println(productQuantity+ "//////////");
//            System.out.println("amount of product null");
//            }
//
//    }
//
//    public void ifGroceryIsKgGLMl(Product product,String productQuantity, WebElement element, int groceryValue,int valueFromProduct, String unit){
//
//        int twinProductValue = valueFromProduct;
//
//        while (groceryValue == valueFromProduct
//                || groceryValue > valueFromProduct
//        ) {
//            //click to the add agan product
//            System.out.println("значение базы данных - "+groceryValue +"значение продукта с сайта - " + valueFromProduct
//            );
//            WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(10));
//            System.out.println(product.getPathToAddMore());
//            element.click();
//            valueFromProduct += twinProductValue;
//        }
//    }
//    //if grocery don`t have unit(kg,g,l,ml) add more products to the card
//    public void ifGroceryUnitIsTK(int groceryValue,Product product){
//
//
//        WebDriverWait wait1 = new WebDriverWait(driver, Duration.ofSeconds(30));
//        for (int i=(groceryValue-2);i>=0;i--){
//                        System.out.println(i+ " считаем покупки количество кликов");
//
//            WebElement element = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(product.getPathToAddMore())));
//            element.click();
//
//        }
//
//    }
//
//    public void scrapeRimi() {
//
//        driver.get(rimi_URL);
//
//
////        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
//        Thread wait = new Thread();
//        try {
//            wait.sleep(500);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
//
//        applyCookie("Kinnita kõik");
//
//
//        LinkedHashMap<String, String> productAndQuantity = getProductAndQuantity();
//
//
//        for (Map.Entry<String, String> entry : productAndQuantity.entrySet()) {
//            String productName = entry.getKey();
//            setProductNameFromDB(productName);
//            setProductQuantity(productQuantity);
//            System.out.println(getProductQuantity());
//            System.out.println(getProductNameFromDB());
//            String productQuantity = entry.getValue();
//
//            System.out.println(productQuantity);
//
//
//            List<Product> products = new ArrayList<>();
//
//            System.out.println(productNameFromDB);
//            searchProduct(productName);
//            //create cheapest product
//            Product product=cheapestProduct(products, productNameFromDB);
//            // add to card
//           addToCard(product.getPathToProductTag());
//           if(productQuantity!= null && !productQuantity.isEmpty() ){
//               System.out.println(productQuantity);
//           }
//
//           addTheRightAmountOfProducts(product,productQuantity);
//            Optional<Grocery> groceryOptional = groceryService.findGroceryByName(productNameFromDB);
//            if (groceryOptional.isPresent()) {
//                Grocery grocery = groceryOptional.get();
//                grocery.setRimiPrice(product.getPrice().toString());
//                System.out.println("**************** CHECK PRICE *********************");
//                System.out.println(grocery.getRimiPrice());
//                System.out.println("**************** CHECK PRICE *********************");
//            }
//
//
//        }
//    }
//
////add product to card
//    public void addToCard(String path){
//        WebDriverWait wait1=new WebDriverWait(driver,Duration.ofSeconds(20));
//        WebElement element = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath(path)));
//        element.click();
//    }
//    //Creating method which create product(find price per unit, and name)
//
//    public Product cheapestProduct(List<Product> products, String productNameFromDB) {
//
//
//        for (int i = 1; i <= 20; i++) {
//
//
//            String productName = driver.findElement(By.xpath("//*[@id=\"main\"]/section/div[1]/div/div[2]/div[1]/div/div[2]/ul/li[" + i + "]/div/div[3]/p[1]")).getText().toLowerCase();
//            String priceStringPerUnit = driver.findElement(By.xpath("//*[@id=\"main\"]/section/div[1]/div/div[2]/div[1]/div/div[2]/ul/li[" + i + "]/div/div[3]/div/div/div[2]/p")).getText();
//            String priceString = driver.findElement(By.xpath("//*[@id=\"main\"]/section/div[1]/div/div[2]/div[1]/div/div[2]/ul/li["+ i +"]/div/div[3]/div/div/div[1]/span")).getText() +
//                    "." +
//                    driver.findElement(By.xpath("//*[@id=\"main\"]/section/div[1]/div/div[2]/div[1]/div/div[2]/ul/li["+ i + "]/div/div[3]/div/div/div[1]/div/sup")).getText();
//
//
//            //take string example (1.00 eur/l)- and create 1,00 for BigDecimal() method
//
//            String productPricePerUnit = priceStringPerUnit.split(" ")[0].replace(',', '.');
//
//            //Checking for similarity
//            if (ProductFilter.containsAllWords(productName, productNameFromDB)) {
//                Product product = new Product(productName, new BigDecimal(productPricePerUnit), new BigDecimal(priceString));
//                System.out.println(productName + "Price for unit: " + productPricePerUnit + " Price for product: " + priceString);
//                product.setPathToProductTag(i);
//                products.add(product);
//            }
//
//        }
//        return productToBuy(products);
//    }
//
//    //Searching cheapest product@
//    public Product productToBuy(List<Product> products){
//        Product cheapestProduct = Collections.min(products,Comparator.comparing(Product::getPricePerUnit));
//        System.out.println(cheapestProduct.toString());
//        return cheapestProduct;
//    }
//
//
}
//
//
//
