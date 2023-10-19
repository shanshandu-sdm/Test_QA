import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import org.testng.Assert;

public class TestAmazonMultipleSearch {
    WebDriver driver;

    @BeforeTest
    void setup() {
        System.setProperty("webdriver.chrome.driver", "/Users/dushanshandoc/Downloads/chromedriver-mac-x64/chromedriver");
        driver = new ChromeDriver();
        driver.get("http://www.amazon.com/");
        driver.manage().window().maximize();
    }

    @AfterTest
    void tearDown() {
        driver.quit();
    }

    @Test(priority = 3, enabled = true)
    @Parameters({"item1", "price1a", "price1b", "price1c"})
    void searchJBLSpeakersComparePrices(String item, String priceA, String priceB, String priceC) throws InterruptedException {
        searchProduct(item, priceA, priceB, priceC);
    }

    @Test(priority = 1, enabled = true)
    @Parameters({"item3", "price3a", "price3b", "price3c"})
    void searchWomensShowsComparePrices(String item, String priceA, String priceB, String priceC) throws InterruptedException {
        searchProduct(item, priceA, priceB, priceC);
    }

    @Test(priority = 2, enabled = true)
    @Parameters({"item2", "price2a", "price2b", "price2c"})
    void searchCellPhoneComparePrices(String item, String priceA, String priceB, String priceC) throws InterruptedException {
        searchProduct(item, priceA, priceB, priceC);
    }

    void searchProduct(String item, String priceA, String priceB, String priceC) throws InterruptedException {
        WebElement searchBox = driver.findElement(By.id("twotabsearchtextbox"));
        searchBox.clear();
        searchBox.sendKeys(item);
        searchBox.submit();

        // For demonstration purposes, we are adding a sleep here to give some time for the search results to load.
        Thread.sleep(5000);
    }
}
