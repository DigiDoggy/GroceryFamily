package GroceryFamily.GroceryDad;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public class Test3 {
    public static void main(String[] args) throws Exception {
        String url = "https://www.rimi.ee/";
        String productName = "kohuke";
        Document doc = Jsoup.connect(url).get();
        Element product = doc.select("div.product:contains(" + productName + ")").first();
        String priceStr = product.select("span.price").text().replace(",", ".");
        double price = Double.parseDouble(priceStr);
        System.out.println("The cheapest price for " + productName + " is " + price);
    }
}